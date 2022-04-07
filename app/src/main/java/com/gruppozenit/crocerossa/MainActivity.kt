package com.gruppozenit.crocerossa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.gruppozenit.crocerossa.allegati.AllegatiListFragment
import com.gruppozenit.crocerossa.message.MessagesListFragment
import com.gruppozenit.crocerossa.utils.PrefManager
import com.gruppozenit.crocerossa.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var receiver: MessagesListFragment.NewMessageBroadcast? = null
    private var prefManager: PrefManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        prefManager = PrefManager.getInstance(this)

        super.onCreate(savedInstanceState)
        /* if (prefManager!!.darkModeONorOFF) {

             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
         } else {
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
         }*/

        setContentView(R.layout.activity_main)

        val messagesListFragment = MessagesListFragment()
        val settingsFragment = SettingsFragment()
        val allegatiListFragment= AllegatiListFragment()

        if (intent.getBooleanExtra("theme_change", false)) {
            if (prefManager!!.darkModeONorOFF) {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            bottomNavigationView.selectedItemId=R.id.settings
            setCurrentFragment(settingsFragment)
        } else {
            setCurrentFragment(messagesListFragment)
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> setCurrentFragment(settingsFragment)
                R.id.message -> setCurrentFragment(messagesListFragment)
                R.id.allegatti->setCurrentFragment(allegatiListFragment)

            }
            true
        }

        Utils.deleteAllOldFiles(getExternalFilesDir(null).toString() + "/" + getString(R.string.app_name))

    }

    private fun setCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragment)
                commit()
            }

}