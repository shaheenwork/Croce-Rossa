package com.gruppozenit.crocerossa.fileView

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.model.messageDetailsModels.MessageAttachment
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.Utils
import com.zenith.eteam.chronology.chronology1.progressdialog.WorkingProgressDialog
import kotlinx.android.synthetic.main.fragment_pdfviewer.*


class PdfViewFragment : Fragment(), OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private var file: MessageAttachment? = null

    private var dialog: WorkingProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_pdfviewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val path = activity!!.getExternalFilesDir(null).toString() + "/" + context!!.getString(R.string.app_name)




        val myDir = java.io.File(path)
        if (!myDir.exists()) {
            myDir.mkdir()
        }

        val config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30000)
                .setConnectTimeout(30000)
                .build()
        PRDownloader.initialize(context, config)
        dialog = WorkingProgressDialog(context!!)
        showProgressDialog()
        val bundle = arguments
        if (bundle != null) {
            file = bundle.getParcelable(Consts.FILE)
        }

        if (java.io.File(getFullFilePath(path)).exists()) {
            Log.d("shnt", "from file")
            displayFromUri(Uri.fromFile(java.io.File(getFullFilePath(path))))
            hideProgressDialog()
        } else {

            Log.d("shnt", "dmnld")
            if (Utils.isInternetAvailable(activity!!)) {
                mDownloadFile(path)
            }
        }

    }

    private fun mDownloadFile(path: String) {
        PRDownloader.download(file!!.filePath, path, "" + file!!.messaggioDetailsID + "-" + file!!.title)
                .build()
                .setOnStartOrResumeListener { }
                .setOnPauseListener { }
                .setOnCancelListener { }
                .setOnProgressListener { }
                .start(object : OnDownloadListener {
                    override fun onError(error: Error?) {

                        hideProgressDialog()

                        Toast.makeText(activity, activity!!.getString(R.string.loading_failed), Toast.LENGTH_LONG).show()
                        Log.e("prdwn", error.toString())


                    }

                    override fun onDownloadComplete() {

                        hideProgressDialog()
                        Log.e("prdwn", "success")

                        displayFromUri(Uri.fromFile(java.io.File(getFullFilePath(path))))

                    }

                })
    }

    private fun getFullFilePath(path: String) =
            path + "/" + +file!!.messaggioDetailsID + "-" + file!!.title

    fun showProgressDialog() {
        if (dialog != null) {
            dialog!!.setCancelable(false)
            dialog!!.show()
        }
    }

    fun hideProgressDialog() {
        if (dialog != null) {
            dialog!!.isShowing
            dialog!!.dismiss()
        }
    }


    private fun displayFromUri(uri: Uri) {
        //  pdfFileName = getFileName(uri)

        pdfView.fromUri(uri)
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .swipeHorizontal(false)
                .scrollHandle(DefaultScrollHandle(activity))
                .spacing(10) // in dp
                .onPageError(this)
                .load()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        // pageNumber = page
        //   activity!!.title = String.format("%s %s / %s", file!!.fileName, page + 1, pageCount)
    }

    override fun loadComplete(nbPages: Int) {

    }

    override fun onPageError(page: Int, t: Throwable?) {
        Toast.makeText(activity, activity!!.getString(R.string.loading_failed), Toast.LENGTH_LONG).show()
    }

}
