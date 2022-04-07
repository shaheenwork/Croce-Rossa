package com.gruppozenit.crocerossa

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.gruppozenit.crocerossa.utils.PrefManager
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    private var prefManager: PrefManager? = null


    companion object {

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_settings, container,
                false)
        val activity = activity as Context


        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
      //  init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

      private fun init(view: View) {
        prefManager = PrefManager.getInstance(activity!!)

          view!!.switch_darkmode.isChecked = prefManager!!.darkModeONorOFF
          view!!. switch_notification.isChecked = prefManager!!.notificationONorOFF
          view!!.switch_passcode.isChecked = prefManager!!.lockONorOFF


          view!!.switch_darkmode.setOnCheckedChangeListener { p0, p1 ->
            prefManager!!.setDARKkMODE_ON_OFF(p1)
            if (p1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
              val intent=Intent(activity,MainActivity::class.java)
              intent.putExtra("theme_change",true)
              startActivity(intent)
              activity!!.finish()



        }

          view!!.switch_passcode.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                val km: KeyguardManager = activity!!.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
                if (km.isKeyguardSecure) {
                    prefManager!!.setLockOnOff(p1)
                } else {
                    Toast.makeText(activity, "Devi prima abilitare una modalità di sblocco nelle impostazioni del dispositivo", Toast.LENGTH_SHORT).show()
                    view!!.switch_passcode.isChecked = false
                }

            } else {
                prefManager!!.setLockOnOff(p1)
            }
        }


          view!!.switch_notification.setOnCheckedChangeListener { p0, p1 ->
            prefManager!!.setNotificationOnOff(p1)
        }


    }

}