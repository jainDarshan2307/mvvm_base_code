package com.example.mvvmbasecode.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.example.mvvmbasecode.R
import com.example.mvvmbasecode.`interface`.SendAnyTwoParameterInterface

fun Context.goToSettingScreenPopUp(sendAnyTwoParameterInterface: SendAnyTwoParameterInterface) {
    val dialog = AlertDialog.Builder(this)
    dialog.setMessage("You need to give some mandatory permissions to continue. Do you want to go to app settings?")
        .setPositiveButton("Yes") { _, _ ->
            sendAnyTwoParameterInterface.sendAction("", true)

            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", this.packageName, null)
            intent.data = uri
            startActivity(intent)
        }.setNegativeButton("Cancel") { _, _ ->
            sendAnyTwoParameterInterface.sendAction("", false)
        }

    val alertDialog = dialog.create()

    alertDialog.setOnShowListener {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(ContextCompat.getColor(this, R.color.black))

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    alertDialog.show()
}
