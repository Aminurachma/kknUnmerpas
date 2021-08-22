package com.example.kkn_unmer.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window

class GPSLocationDialog(context : Context, message : String = "", val onClose : () -> Unit, val onActionOK : () -> Unit) : Dialog(context){

    private val message: String

    init {
        this.message = message
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

      /*  setContentView(R.layout.layout_gps_location_dialog)

        if(message.isNotEmpty()) dialog_message.text = message

        dialog_action_close.setOnClickListener {
            dismiss()
            onClose()
        }

        dialog_action_oke.setOnClickListener {
            dismiss()
            onActionOK()
        }*/
    }
}