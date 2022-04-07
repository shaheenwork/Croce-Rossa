package com.zenith.eteam.chronology.chronology1.progressdialog

import android.content.Context
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialog
import com.gruppozenit.crocerossa.R


class WorkingProgressDialog(context: Context) : AppCompatDialog(context, R.style.TransparentProgressDialog) {
    private var iv: ImageView? = null

    init {
        setContentView(R.layout.progress_dialog)
        iv = findViewById(R.id.imageView)
    }

/*
    protected constructor(context: Context, isCancellable: Boolean) : super(context, R.style.TransparentProgressDialog) {
        setContentView(R.layout.progress_dialog)
        setCancelable(isCancellable)
        iv = findViewById(R.id.imageView)
    }*/

    override fun show() {
        val anim = RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 500
        iv!!.animation = anim
        iv!!.startAnimation(anim)
        try {
            super.show()
        } catch (e: Exception) {
        }
    }

}
