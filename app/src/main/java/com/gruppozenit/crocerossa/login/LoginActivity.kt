package com.gruppozenit.crocerossa.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.gruppozenit.crocerossa.MainActivity
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.model.deviceRegModels.DeviceRegInput
import com.gruppozenit.crocerossa.model.deviceRegModels.DeviceRegResponse
import com.gruppozenit.crocerossa.model.soceitaRuoloListModels.GetSocietaRuoloListResponse
import com.gruppozenit.crocerossa.model.soceitaRuoloListModels.Ruolo
import com.gruppozenit.crocerossa.model.soceitaRuoloListModels.Societum
import com.gruppozenit.crocerossa.network.provider.RetrofitProvider
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager
import com.gruppozenit.crocerossa.utils.Utils
import com.zenith.eteam.chronology.chronology1.progressdialog.WorkingProgressDialog
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var prefManager: PrefManager? = null
    private var deviceRegInput: DeviceRegInput? = null
    private var dialog: WorkingProgressDialog? = null
    private var societaList: List<Societum>? = null
    private var ruoloaList: List<Ruolo>? = null
    private var selected_societa_id: Int? = null
    private var selected_societa_name: String? = null
    private var selected_ruolo_id: Int? = null
    private var selected_ruolo_name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

    }

    private fun init() {


        dialog = WorkingProgressDialog(this)
        prefManager = PrefManager.getInstance(this)
        Utils.clearUserDetails(prefManager!!)
        prefManager!!.setDeviceId(0)

        generateFcmToken()

        if (Utils.isInternetAvailable(this@LoginActivity)) {
          //  mGetSocietaAndRuoloList()
        }


        btn_login.setOnClickListener(this)
        tv_ruolo.setOnClickListener(this)
        tv_societa.setOnClickListener(this)


    }

    private fun generateFcmToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this) { instanceIdResult: InstanceIdResult ->
            val newToken = instanceIdResult.token
            prefManager!!.fcmToken = newToken
        }
    }


    private fun mGetSocietaAndRuoloList() {

        showProgressDialog()


        val api =
            RetrofitProvider.getInstance().retrofit!!.create(com.zenith.eteam.chronology.chronology1.network.api.Api::class.java)

        val apiCall = api.mGetSocietaAndRuoloList(Consts.AUTH_KEY)
        apiCall.enqueue(object : Callback<GetSocietaRuoloListResponse> {
            override fun onResponse(
                call: Call<GetSocietaRuoloListResponse>,
                response: Response<GetSocietaRuoloListResponse>
            ) {

                hideProgressDialog()
                if (response.body() != null) {
                    if (response.body()!!.result.success == true) {

                        if (response.body()!!.result.data != null && response.body()!!.result.data.societa != null) {
                            societaList = response.body()!!.result.data.societa
                        }
                        if (response.body()!!.result.data != null && response.body()!!.result.data.ruolo != null) {
                            ruoloaList = response.body()!!.result.data.ruolo
                        }

                    } else {
                        hideProgressDialog()
                        Toast.makeText(
                            this@LoginActivity,
                            response.body()!!.result.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

            override fun onFailure(call: Call<GetSocietaRuoloListResponse>, t: Throwable) {
                hideProgressDialog()
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.failed_try_again),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }


    private fun mRegisterDevice() {


        showProgressDialog()

        generateApiInput()

        val api =
            RetrofitProvider.getInstance().retrofit!!.create(com.zenith.eteam.chronology.chronology1.network.api.Api::class.java)
        val apiCall = api.mRegisterDevice(deviceRegInput!!)
        apiCall.enqueue(object : Callback<DeviceRegResponse> {
            override fun onResponse(
                call: Call<DeviceRegResponse>,
                response: Response<DeviceRegResponse>
            ) {

                hideProgressDialog()

                if (response.body()!!.result.success == true) {

                    if (response.body()!!.result.data != null && response.body()!!.result.data.tokenStatus == Consts.TOKEN_ALREADY_EXIST_STATUS) {

                        val alertDialog: AlertDialog = AlertDialog.Builder(
                            this@LoginActivity, R.style.AlertDialogTheme
                        ).create()

                        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        alertDialog.setMessage(response.body()!!.result.message)


                        alertDialog.setButton(
                            AlertDialog.BUTTON_POSITIVE,
                            getString(R.string.ok)
                        ) { dialog, which ->
                            deviceRegInput!!.tokenStatus = Consts.TOKEN_ALREADY_EXIST_STATUS
                            mRegisterDevice()

                        }

                        alertDialog.setButton(
                            AlertDialog.BUTTON_NEGATIVE,
                            getString(R.string.annula)
                        ) { dialog, which ->
                            alertDialog.dismiss()
                        }




                        alertDialog.show()


                    } else {

                        proceed(response)
                    }

                } else {
                    Utils.clearUserDetails(prefManager!!)
                    prefManager!!.setDeviceId(0)
                    Toast.makeText(
                        this@LoginActivity,
                        response.body()!!.result.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    generateFcmToken()
                }

            }

            override fun onFailure(call: Call<DeviceRegResponse>, t: Throwable) {
                hideProgressDialog()
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.failed_to_connect_server),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }

    private fun generateApiInput() {
        deviceRegInput!!.nome = et_nome!!.text.toString().trim()
        deviceRegInput!!.cognome = et_cognome!!.text.toString().trim()
        deviceRegInput!!.deviceToken = prefManager!!.fcmToken
        deviceRegInput!!.deviceID = prefManager!!.deviceID
       // deviceRegInput!!.societa_Id = selected_societa_id!!.toInt()
       // deviceRegInput!!.ruolo_Id = selected_ruolo_id!!.toInt()
        deviceRegInput!!.email = et_email!!.text.toString().trim()
    }

    private fun proceed(response: Response<DeviceRegResponse>) {

        setValuesToPref(response)

        if (response.body()!!.result.data != null && response.body()!!.result.data.approvedStatus != null && response.body()!!.result.data.approvedStatus) {

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            val intent = Intent(this@LoginActivity, LoginWaitActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setValuesToPref(response: Response<DeviceRegResponse>) {
        prefManager!!.setInfoPageFlag(false)

        if (response.body()!!.result.data != null && response.body()!!.result.data.deviceID != null) {

            prefManager!!.setDeviceId(response.body()!!.result.data.deviceID.toInt())

        }

        prefManager!!.nome = deviceRegInput!!.nome
        prefManager!!.cognome = deviceRegInput!!.cognome
        prefManager!!.mail = deviceRegInput!!.email
        prefManager!!.societa = selected_societa_name
        prefManager!!.ruolo = selected_ruolo_name
        prefManager!!.isLogin = true
        prefManager!!.setIsApproved(Consts.ACCESS_PENDING)
    }


    private fun showSocietaList() {

        val charSequences: MutableList<CharSequence> = ArrayList()

        for (i in societaList!!.indices) {
            charSequences.add(societaList!![i].societaName)
        }

        val items: Array<CharSequence> = charSequences.toTypedArray()

        val dialogBuilder: AlertDialog.Builder =
            AlertDialog.Builder(this@LoginActivity, R.style.AlertDialogTheme)
        dialogBuilder.setTitle(getString(R.string.label_Societa))
        dialogBuilder.setItems(items)
        { dialog, item ->
            tv_societa.text = societaList!![item].societaName
            selected_societa_id = societaList!![item].societaId
            selected_societa_name = societaList!![item].societaName
        }
        val alertDialogObject: AlertDialog = dialogBuilder.create()

        alertDialogObject.show()


    }

    private fun showRuoloList() {

        val charSequences: MutableList<CharSequence> = ArrayList()

        for (i in ruoloaList!!.indices) {
            charSequences.add(ruoloaList!![i].ruoloName)
        }

        val items = charSequences.toTypedArray()

        val dialogBuilder: AlertDialog.Builder =
            AlertDialog.Builder(this@LoginActivity, R.style.AlertDialogTheme)
        dialogBuilder.setTitle(getString(R.string.label_Ruolo))
        dialogBuilder.setItems(items) { dialog, item ->
            tv_ruolo.text = ruoloaList!![item].ruoloName
            selected_ruolo_id = ruoloaList!![item].ruoloId
            selected_ruolo_name = ruoloaList!![item].ruoloName
        }
        val alertDialogObject: AlertDialog = dialogBuilder.create()
        alertDialogObject.show()

    }


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

    override fun onClick(view: View?) {

        if (view == btn_login) {
            if (TextUtils.isEmpty(et_nome!!.text.toString()) || TextUtils.isEmpty(et_cognome!!.text.toString()) || TextUtils.isEmpty(
                    et_email!!.text.toString())
                //) || selected_societa_id == null || selected_ruolo_id == null
            ) {
                Toast.makeText(
                    LoginActivity@ this,
                    getString(R.string.fill_all_fields),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (Utils.isInternetAvailable(this@LoginActivity)) {
                    deviceRegInput = DeviceRegInput()
                    mRegisterDevice()
                }
            }
        } else if (view == tv_ruolo) {
            if (ruoloaList != null) {
                et_cognome.clearFocus()
                et_nome.clearFocus()
                tv_societa.clearFocus()
                tv_ruolo.requestFocus()
                showRuoloList()
            }
        } else if (view == tv_societa) {
            if (societaList != null) {
                et_cognome.clearFocus()
                et_nome.clearFocus()
                tv_ruolo.clearFocus()
                tv_societa.requestFocus()
                showSocietaList()
            }
        }

    }


}
