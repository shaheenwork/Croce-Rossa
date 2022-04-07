package com.zenith.eteam.chronology.chronology1.progressdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseProgressDialog : AppCompatActivity() {
    private var dialog: WorkingProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = WorkingProgressDialog(this)
    }

    fun showProgressDialog() {
        if(dialog!=null) {
            dialog!!.setCancelable(false)
            dialog!!.show()
        }

    }

    fun hideProgressDialog() {
        if(dialog!=null) {
            dialog!!.isShowing
            dialog!!.dismiss()

        }

    }
}
