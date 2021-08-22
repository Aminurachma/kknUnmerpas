package com.example.kkn_unmer.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.example.kkn_unmer.R

class AppProgressDialog(context : Context, message : String = "") : Dialog(context){

    private val message: String = message

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_progress_dialog)
//        textLoading.text = message
    }
}