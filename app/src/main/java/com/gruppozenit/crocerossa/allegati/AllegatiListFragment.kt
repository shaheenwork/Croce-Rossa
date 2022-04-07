package com.gruppozenit.crocerossa.allegati

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.navigation.NavigationView
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.adapter.AllegattiAdapter
import com.gruppozenit.crocerossa.fileView.FileViewActivity
import com.gruppozenit.crocerossa.fileView.NoOrientationConfigChangeFileViewActivity
import com.gruppozenit.crocerossa.login.LoginActivity
import com.gruppozenit.crocerossa.login.LoginWaitActivity
import com.gruppozenit.crocerossa.model.attachmentListModels.AttachmentList
import com.gruppozenit.crocerossa.model.attachmentListModels.GetAttachmentListResponse
import com.gruppozenit.crocerossa.model.messageDetailsModels.MessageAttachment
import com.gruppozenit.crocerossa.network.provider.RetrofitProvider
import com.gruppozenit.crocerossa.utils.ConnectivityReceiver
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager
import com.gruppozenit.crocerossa.utils.Utils
import com.zenith.eteam.chronology.chronology1.progressdialog.WorkingProgressDialog
import kotlinx.android.synthetic.main.fragment_messages_list.*
import kotlinx.android.synthetic.main.fragment_messages_list.view.*
import kotlinx.android.synthetic.main.top_bar_settings.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap


class AllegatiListFragment : Fragment(), AllegattiAdapter.ItemClick, ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener {


    private var adapterEntries: AllegattiAdapter? = null

    private var prefManager: PrefManager? = null
    private var dialog: WorkingProgressDialog? = null

    private var receiver: NewMessageBroadcast? = null
    private val BUNDLE_RECYCLER_LAYOUT = "messageslist.recycler.layout"
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var savedRecyclerLayoutState: Parcelable? = null
    private var nav: NavigationView? = null;

    companion object {

        fun newInstance(): AllegatiListFragment {
            return AllegatiListFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_allegati_list, container,
                false)
        val activity = activity as Context

        val itemDecoration = DividerItemDecoration(activity, VERTICAL)
        itemDecoration.setDrawable(this!!.resources.getDrawable(R.drawable.divider))


        view.rv_messages!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.rv_messages!!.isNestedScrollingEnabled = true
        view.rv_messages!!.setHasFixedSize(true)
        view.rv_messages!!.addItemDecoration(itemDecoration)


        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        init()
    }

    override fun onResume() {

        registerReceiver()
        if (Utils.isInternetAvailable(activity!!)) {
            mGetAllegatiList()
        }

        super.onResume()
    }

    override fun itemClickEvent(item: AttachmentList) {

        if (Utils.isInternetAvailable(activity!!)) {

            val file = MessageAttachment( item.messaggioDetailsID,item.filePath,item.title,item.type)

            if (item.type == Consts.FILE_TYPE_VIDEO || item.type == Consts.FILE_TYPE_AUDIO || item.type == Consts.FILE_TYPE_PDF) {
                val intent = Intent(activity!!, NoOrientationConfigChangeFileViewActivity::class.java)
                intent.putExtra(Consts.FILE, file)
                startActivity(intent)
            } else {
                val intent = Intent(activity!!, FileViewActivity::class.java)
                intent.putExtra(Consts.FILE, file)
                startActivity(intent)
            }
        }

    }

    override fun onPause() {

        super.onPause()

        if (receiver != null) {
            Objects.requireNonNull(activity!!).unregisterReceiver(receiver)
        }
        if (connectivityReceiver != null) {
            Objects.requireNonNull(activity!!).unregisterReceiver(connectivityReceiver)
        }

        savedRecyclerLayoutState = rv_messages.layoutManager!!.onSaveInstanceState()


    }


    private fun init() {


        prefManager = PrefManager.getInstance(activity!!)

        receiver = NewMessageBroadcast()
        connectivityReceiver = ConnectivityReceiver()
        registerReceiver()


        dialog = WorkingProgressDialog(activity!!)


    }

    private fun mGetAllegatiList() {

        showProgressDialog()


        val api = RetrofitProvider.getInstance().retrofit!!.create(com.zenith.eteam.chronology.chronology1.network.api.Api::class.java)
        val hashMap = HashMap<String, Int>()

        hashMap[Consts.KEY_DEVICE_ID] = prefManager!!.deviceID

        val apiCall = api.mGetAttachmentList(prefManager!!.fcmToken, hashMap)
        apiCall.enqueue(object : Callback<GetAttachmentListResponse> {
            override fun onResponse(call: Call<GetAttachmentListResponse>, response: Response<GetAttachmentListResponse>) {

                if (response.body() != null) {
                    if (response.body()!!.result.success == true) {


                        if (response.body()!!.result.data.attachmentList != null) {
                            showAllegatiInRecyclerView(response.body()!!.result.data.attachmentList)
                        }

                    } else {
                        hideProgressDialog()
                    }
                }

            }

            override fun onFailure(call: Call<GetAttachmentListResponse>, t: Throwable) {
                hideProgressDialog()
                Toast.makeText(activity!!, getString(R.string.failed_to_connect_server), Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun showAllegatiInRecyclerView(data: MutableList<AttachmentList>) {

        if (data.size != 0) {

            adapterEntries = AllegattiAdapter(data, this, activity!!)
            adapterEntries!!.setHasStableIds(true)
            rv_messages!!.adapter = adapterEntries
            adapterEntries!!.notifyDataSetChanged()

            if (savedRecyclerLayoutState != null) {
                rv_messages.layoutManager!!.onRestoreInstanceState(savedRecyclerLayoutState)
            }


        }
        hideProgressDialog()

    }


    internal inner class NewMessageBroadcast : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            try {

                mGetAllegatiList()

            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }

    private fun registerReceiver() {
        ConnectivityReceiver.connectivityReceiverListener = this
        try {
            val intentFilter = IntentFilter()
            intentFilter.addAction(Consts.ACTION_NEW_MESSAGE)
            intentFilter.addAction(Consts.ACTION_ADMIN_STATUS)
            Objects.requireNonNull(activity!!).registerReceiver(receiver, intentFilter)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        try {
            Objects.requireNonNull(activity!!).registerReceiver(connectivityReceiver, null)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun showProgressDialog() {
        if (dialog != null) {
            dialog!!.setCancelable(false)
            dialog!!.show()
        }
    }

    fun hideProgressDialog() {
        try {
            if (dialog != null) {
                dialog!!.isShowing
                dialog!!.dismiss()
            }
        } catch (e: Exception) {
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            mGetAllegatiList()
        }
    }

    override fun onClick(p0: View?) {
        if (p0 == btn_settings) {
        }
    }

}
