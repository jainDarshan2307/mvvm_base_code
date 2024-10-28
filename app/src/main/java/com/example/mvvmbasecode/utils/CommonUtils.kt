package com.example.mvvmbasecode.utils

import android.app.Dialog
import android.content.Context
import android.graphics.*
import androidx.core.graphics.drawable.toDrawable
import com.example.mvvmbasecode.R
import com.example.mvvmbasecode.model.ErrorResponse
import com.example.mvvmbasecode.network.APIState
import com.google.gson.Gson
import retrofit2.HttpException

object CommonUtils {

    fun showLoadingDialog(context: Context): Dialog {
        val progressDialog = Dialog(context)

        progressDialog.let {
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.loading_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }

    fun manageErrorMessage(e: HttpException): String {
        val thing: ErrorResponse?
        when {
            e.code() == 422 -> {
                val errorResponse = e.response()!!.errorBody()!!.string()
                val gson = Gson()
                thing = gson.fromJson(errorResponse, ErrorResponse::class.java)

            }

            e.code() == 400 -> {
                val errorResponse = e.response()!!.errorBody()!!.string()
                val gson = Gson()
                thing = gson.fromJson(errorResponse, ErrorResponse::class.java)
            }

            e.code() == 405 -> {
                val errorResponse = e.response()!!.errorBody()!!.string()
                val gson = Gson()
                thing = gson.fromJson(errorResponse, ErrorResponse::class.java)

            }

            e.code() == 500 -> {
                val errorResponse = e.response()!!.errorBody()!!.string()
                val gson = Gson()

                thing = gson.fromJson(errorResponse, ErrorResponse::class.java)
            }

            e.code() == 401 -> {
                val errorResponse = e.response()!!.errorBody()!!.string()
                val gson = Gson()
                thing = gson.fromJson(errorResponse, ErrorResponse::class.java)
            }

            else -> {
                val errorResponse = e.response()!!.errorBody()!!.string()
                val gson = Gson()
                thing = gson.fromJson(errorResponse, ErrorResponse::class.java)
            }
        }

        return thing?.message!!
    }
}