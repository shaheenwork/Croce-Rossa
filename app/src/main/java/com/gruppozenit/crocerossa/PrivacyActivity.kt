package com.gruppozenit.crocerossa

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gruppozenit.crocerossa.login.LoginActivity
import kotlinx.android.synthetic.main.activity_info.*

class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        btn_avanti.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@PrivacyActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        })
    }


}