package com.example.mvvmbasecode.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmbasecode.database.userdata.UserDataEntity
import com.example.mvvmbasecode.network.APIState
import com.example.mvvmbasecode.repository.MainRepository
import com.example.mvvmbasecode.utils.CommonUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    val _stateUserData = MutableStateFlow<APIState<ArrayList<UserDataEntity>>>(APIState.Idle())
    val stateUserData: StateFlow<APIState<ArrayList<UserDataEntity>>> get() = _stateUserData

    fun getUserData() {
        _stateUserData.value = APIState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                mainRepository.getData(10, 0).catch { error ->
                    if (error is HttpException) {
                        _stateUserData.value = APIState.Error(CommonUtils.manageErrorMessage(error))
                    } else {
                        error.printStackTrace()
                        _stateUserData.value =
                            if (error.message != null) APIState.Error(error.message!!) else {
                                APIState.Error(error.printStackTrace().toString())
                            }
                    }
                }.collect { response ->
                    _stateUserData.value = APIState.Success(response.body()?.users ?: arrayListOf())
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
    }
}