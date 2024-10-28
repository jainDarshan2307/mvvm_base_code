package com.example.mvvmbasecode.network

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmbasecode.R
import com.example.mvvmbasecode.utils.Constants

class SessionManager(context: Context) {
    private var editor: SharedPreferences.Editor
    private val pref: SharedPreferences


    fun getDeviceToken(): String? {
        return pref.getString(
            Constants.PREF_DEVICE_TOKEN, "test"
        )
    }

    fun setDeviceToken(isTrue: String) {
        editor.putString(Constants.PREF_DEVICE_TOKEN, isTrue)
        editor.commit()
    }

//    fun saveAuthorizedUser(authorizedUser: AuthenticationModel?) {
//        val json = Gson().toJson(authorizedUser)
//        editor.putString(KEY_AUTHORIZED_USER, json).apply()
//    }
//
//    fun getAuthorizedUser(): AuthenticationModel? {
//        val json = pref.getString(KEY_AUTHORIZED_USER, "")
//        return Gson().fromJson(json, AuthenticationModel::class.java)
//    }

//
//    fun saveEventDetailsData(authorizedUser: AuthenticationModel?) {
//        val json = Gson().toJson(authorizedUser)
//        editor.putString(KEY_UPCOMING_EVENTS_DETAILS_DATA, json).apply()
//    }
//
//    fun getEventDetailsData(): AuthenticationModel? {
//        val json = pref.getString(KEY_UPCOMING_EVENTS_DETAILS_DATA, "")
//        return Gson().fromJson(json, AuthenticationModel::class.java)
//    }

    var isLogin: Boolean?
        get() = pref.getBoolean(KEY_IS_LOGIN, false)
        set(isLogin) {
            editor.putBoolean(KEY_IS_LOGIN, isLogin!!)
            editor.commit()
        }

    var isSocialLogin: Boolean?
        get() = pref.getBoolean(KEY_IS_SOCIAL_LOGIN, false)
        set(isLogin) {
            editor.putBoolean(KEY_IS_SOCIAL_LOGIN, isLogin!!)
            editor.commit()
        }

    fun setLogout() {
        editor.clear().apply()
    }

    companion object {
        const val KEY_AUTHORIZED_USER = "KEY_AUTHORIZED_USER"
        const val KEY_IS_LOGIN = "is_login"
        const val KEY_IS_SOCIAL_LOGIN = "is_social_login"
    }

    init {
        val prefName = context.resources.getString(R.string.app_name)
        val privateMode = 0
        pref = context.getSharedPreferences(prefName, privateMode)
        editor = pref.edit()
    }
}