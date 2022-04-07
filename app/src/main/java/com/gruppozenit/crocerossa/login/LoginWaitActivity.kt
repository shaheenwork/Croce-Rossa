package com.gruppozenit.crocerossa.login

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gruppozenit.crocerossa.MainActivity
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager
import kotlinx.android.synthetic.main.activity_login_wait.*
import java.util.*


class LoginWaitActivity : AppCompatActivity() {

    private var prefManager: PrefManager? = null
    private var receiver: AdminStatusBroadCastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_wait)

        init()
    }


    override fun onResume() {
        super.onResume()
        registerReceiver()
    }

    override fun onPause() {

        super.onPause()

        if (receiver != null) {
            Objects.requireNonNull(baseContext).unregisterReceiver(receiver)
        }
    }

    private fun init() {

        prefManager = PrefManager.getInstance(this)
        receiver = AdminStatusBroadCastReceiver()
        tv_nome.text = prefManager!!.nome
        tv_cognome.text = prefManager!!.cognome
        tv_societa.text = prefManager!!.societa
        tv_ruolo.text = prefManager!!.ruolo

    }


    private fun registerReceiver() {
        try {
            val intentFilter = IntentFilter()
            intentFilter.addAction(Consts.ACTION_ADMIN_STATUS)
            Objects.requireNonNull(baseContext).registerReceiver(receiver, intentFilter)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    internal inner class AdminStatusBroadCastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            try {

                val access = intent.getIntExtra(Consts.KEY_ACCESS, -1)
                moveAccordingToAccess(access)

            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }

    private fun moveAccordingToAccess(access: Int) {

        var intent: Intent? = null
        if (access == Consts.ACCESS_APPROVED) {
            intent = Intent(this@LoginWaitActivity, MainActivity::class.java)

        } else if (access == Consts.ACCESS_REJECTED || access == Consts.ACCESS_REMOVED) {
            intent = Intent(this@LoginWaitActivity, LoginActivity::class.java)

        }
        if (intent != null) {
            startActivity(intent)
            finish()
        }

    }


}
