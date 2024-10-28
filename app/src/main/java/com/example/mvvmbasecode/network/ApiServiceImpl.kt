package com.example.mvvmbasecode.network

import com.example.mvvmbasecode.model.UserDataResponse
import retrofit2.Response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: APIService) {

//    suspend fun signupAPI(signUpBody: RequestBodies.SignUpBody): AuthenticationModel =
//        apiService.signupAPI(signUpBody)

    suspend fun getUserData(limit: Int, skip: Int): Response<UserDataResponse> = apiService.getUserData(limit = limit, skip = skip)
}