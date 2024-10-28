package com.example.mvvmbasecode.repository

import android.util.Log
import com.example.mvvmbasecode.database.RoomDB
import com.example.mvvmbasecode.model.UserDataResponse
import com.example.mvvmbasecode.network.APIState
import com.example.mvvmbasecode.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServices: ApiServiceImpl) :
    BaseRepository() {

    fun getData(limit: Int, skip: Int): Flow<Response<UserDataResponse>> = flow {
        when (val result = safeAPICall { apiServices.getUserData(limit = limit, skip = skip) }) {
            is APIState.Success -> {
                emit(result.response)
            }  // Emit the successful response
            is APIState.Error -> throw Exception(result.error)  // Throw an exception to handle it downstream
            is APIState.Idle -> {}
            is APIState.Loading -> {}
        }
    }.flowOn(Dispatchers.IO)


//    suspend fun signupAPI(signUpBody: RequestBodies.SignUpBody): AuthenticationModel =
//        apiService.signupAPI(signUpBody)

}