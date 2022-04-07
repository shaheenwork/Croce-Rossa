package com.gruppozenit.crocerossa.fileView

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.model.attachmentStatusModels.UpdateAttachmentInput
import com.gruppozenit.crocerossa.model.attachmentStatusModels.AttachmentUpdateResponse
import com.gruppozenit.crocerossa.model.messageDetailsModels.MessageAttachment
import com.gruppozenit.crocerossa.network.provider.RetrofitProvider
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager
import com.zenith.eteam.chronology.chronology1.progressdialog.WorkingProgressDialog
import kotlinx.android.synthetic.main.activity__noconfig_file_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NoOrientationConfigChangeFileViewActivity : AppCompatActivity(), View.OnClickListener {

    private var file: MessageAttachment? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var prefManager: PrefManager? = null


    private var dialog: WorkingProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //  this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity__noconfig_file_view)

        btn_back_noconfig.setOnClickListener(this)


        prefManager = PrefManager.getInstance(this)

        loadFile()

        updateReadStatusStatus()

        //enlarge hit area
        val parent = btn_back_noconfig.getParent() as View

        parent.post {
            val rect = Rect()
            btn_back_noconfig.getHitRect(rect)
            rect.top -= 50 // increase top hit area
            rect.left -= 50 // increase left hit area
            rect.bottom += 50 // increase bottom hit area
            rect.right += 50 // increase right hit area
            parent.touchDelegate = TouchDelegate(rect, btn_back_noconfig)
        }


    }

    private fun loadFile() {

        file = intent.getParcelableExtra(Consts.FILE)




        fragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putParcelable(Consts.FILE, file)

        if (file!!.type == Consts.FILE_TYPE_PDF) {
            //loadpdf viewer
            val pdfViewFragment = PdfViewFragment()
            pdfViewFragment.arguments = bundle
            fragmentTransaction!!.replace(R.id.container_fileview, pdfViewFragment)

        } else if (file!!.type == Consts.FILE_TYPE_IMAGE) {
            val imageViewFragment = ImageViewFragment()
            imageViewFragment.arguments = bundle
            fragmentTransaction!!.replace(R.id.container_fileview, imageViewFragment)
        } else if (file!!.type == Consts.FILE_TYPE_VIDEO || file!!.type == Consts.FILE_TYPE_AUDIO) {
            val videoAudioFragment = VideoAudioPlayFragment()
            videoAudioFragment.arguments = bundle
            fragmentTransaction!!.replace(R.id.container_fileview, videoAudioFragment)
        }
        fragmentTransaction!!.commit()

    }


    private fun updateReadStatusStatus() {

        val api = RetrofitProvider.getInstance().retrofit!!.create(com.zenith.eteam.chronology.chronology1.network.api.Api::class.java)
        val apiCall = api.mUpdateAttachmentReadStatus(prefManager!!.fcmToken, UpdateAttachmentInput(prefManager!!.deviceID, file!!.messaggioDetailsID))
        apiCall.enqueue(object : Callback<AttachmentUpdateResponse> {
            override fun onResponse(call: Call<AttachmentUpdateResponse>, response: Response<AttachmentUpdateResponse>) {


                if (response.body()!!.result.success == true) {

                    //

                } else {

                    //
                }

            }


            override fun onFailure(call: Call<AttachmentUpdateResponse>, t: Throwable) {
                Toast.makeText(this@NoOrientationConfigChangeFileViewActivity, getString(R.string.failed_to_connect_server), Toast.LENGTH_SHORT).show()
            }
        })


    }

    override fun onClick(view: View?) {
        if (view == btn_back_noconfig) {
            super.onBackPressed()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            top_bar1.visibility = View.GONE
            btn_back_noconfig.visibility = View.GONE
        } else {
            top_bar1.visibility = View.VISIBLE
            btn_back_noconfig.visibility = View.VISIBLE
        }

    }
}
