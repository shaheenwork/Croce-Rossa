package com.gruppozenit.crocerossa.message

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
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.adapter.MessagesAdapter
import com.gruppozenit.crocerossa.login.LoginActivity
import com.gruppozenit.crocerossa.login.LoginWaitActivity
import com.gruppozenit.crocerossa.model.messageListModels.GetMessageListResponse
import com.gruppozenit.crocerossa.model.messageListModels.MessageList
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


class MessagesListFragment : Fragment(), MessagesAdapter.ItemClick, ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener {


    private var adapterEntries: MessagesAdapter? = null

    private var prefManager: PrefManager? = null
    private var dialog: WorkingProgressDialog? = null

    private var receiver: NewMessageBroadcast? = null
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var savedRecyclerLayoutState: Parcelable? = null


    companion object {

        fun newInstance(): MessagesListFragment {
            return MessagesListFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_messages_list, container,
                false)
        val activity = activity as Context

        val itemDecoration = DividerItemDecoration(activity, VERTICAL)
        itemDecoration.setDrawable(this!!.resources.getDrawable(R.drawable.divider))


        view.rv_messages!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.rv_messages!!.isNestedScrollingEnabled = true
        view.rv_messages!!.setHasFixedSize(true)
        view. rv_messages!!.addItemDecoration(itemDecoration)


        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        init()
    }

    override fun onResume() {

        registerReceiver()
        if (Utils.isInternetAvailable(activity!!)) {
            mGetMessages(true)
        }
      //  invalidateOptionsMenu()
        super.onResume()
    }

    override fun itemClickEvent(item: MessageList) {

        if (Utils.isInternetAvailable(activity!!)) {
            val intent = Intent(activity!!, MessageDetailsActivity::class.java)
            intent.putExtra(Consts.KEY_MSG_ID, item.messaggioId)
            intent.putExtra(Consts.KEY_MSG_DATE, item.messageDate)
            startActivity(intent)
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

    private fun mGetMessages(checkStatus: Boolean) {

        showProgressDialog()


        val api = RetrofitProvider.getInstance().retrofit!!.create(com.zenith.eteam.chronology.chronology1.network.api.Api::class.java)
        val hashMap = HashMap<String, Int>()

        hashMap[Consts.KEY_DEVICE_ID] = prefManager!!.deviceID

        val apiCall = api.mGetMessages(prefManager!!.fcmToken, hashMap)
        apiCall.enqueue(object : Callback<GetMessageListResponse> {
            override fun onResponse(call: Call<GetMessageListResponse>, response: Response<GetMessageListResponse>) {

                if (response.body() != null) {
                    if (response.body()!!.result.success == true) {


                        if (response.body()!!.result.data.messageList != null) {
                            setStatusInPref(response.body()!!.result.data.adminStatus.toInt())
                            showMessagesInRecyclerView(response.body()!!.result.data.messageList)
                        }

                    } else {
                        hideProgressDialog()

                        if (checkStatus) {
                            checkStatusAndMove(response)
                        }

                    }
                }

            }

            override fun onFailure(call: Call<GetMessageListResponse>, t: Throwable) {
                hideProgressDialog()
                Toast.makeText(activity!!, getString(R.string.failed_to_connect_server), Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun setStatusInPref(status: Int) {

        if (status == -1) {
            prefManager!!.setIsApproved(Consts.ACCESS_PENDING)
        } else if (status == 1) {
            prefManager!!.setIsApproved(Consts.ACCESS_APPROVED)
        } else {
            prefManager!!.setIsApproved(Consts.ACCESS_REJECTED)
        }


    }

    private fun checkStatusAndMove(response: Response<GetMessageListResponse>) {
        if (response.body()!!.result.data.adminStatus.toInt() == Consts.ACCESS_PENDING) {
            val intent = Intent(activity!!, LoginWaitActivity::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            activity!!.finish()
        } else if (response.body()!!.result.data.adminStatus.toInt() == Consts.ACCESS_REJECTED || response.body()!!.result.data.adminStatus.toInt() == Consts.ACCESS_REMOVED) {

            prefManager!!.isLogin = false

            if (response.body()!!.result.data.adminStatus.toInt() == Consts.ACCESS_REMOVED) {
                prefManager!!.setDeviceId(0)
            }

            Utils.clearUserDetails(prefManager!!)
            val intent = Intent(activity!!, LoginActivity::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            activity!!.finish()

        }
    }

    private fun showMessagesInRecyclerView(data: MutableList<MessageList>) {

        if (data.size != 0) {

            adapterEntries = MessagesAdapter(data, this, activity!!)
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

                mGetMessages(true)

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
            mGetMessages(false)
        }
    }

    override fun onClick(p0: View?) {
    }
}
