package com.example.mvvmbasecode.model

import androidx.activity.result.ActivityResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PermissionViewModel : ViewModel() {
    var flag = MutableLiveData(false)
    var permissionIntent: MutableLiveData<ActivityResult> = MutableLiveData()

    fun allowPermission(flag: Boolean) {
        this.flag.value = flag
    }

    fun mutableLiveData(intent: ActivityResult) {
        permissionIntent.value = intent
    }
}