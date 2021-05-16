package com.thangadurai.saveotask.ui.activity.base

import android.app.Dialog
import android.content.Context
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thangadurai.saveotask.R

open class BaseActivity : AppCompatActivity() {


    var currentToast: Toast? = null
    fun showToast(message: String) {
        currentToast?.cancel()
        currentToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        currentToast?.show()
    }

    private lateinit var progressDialog: Dialog
    fun showProgress() {
        progressDialog = Dialog(this)

        progressDialog.apply {
            setContentView(R.layout.dialog_progress)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }

    }

    fun hideProgress() {
        progressDialog.apply {
            dismiss()
        }
    }

    fun startAnimation(
        view: View,
        animFile: Int
    ) {
        val anim = AnimationUtils.loadAnimation(
            this,
            animFile
        )
        view.startAnimation(anim)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}