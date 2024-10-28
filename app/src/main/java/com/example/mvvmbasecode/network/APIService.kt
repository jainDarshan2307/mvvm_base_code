package com.example.mvvmbasecode.network

import com.example.mvvmbasecode.model.UserDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

//    @Headers("Accept: application/json")
//    @POST("auth/login")
//    suspend fun loginAPI(@Body login: RequestBodies.LoginBody): AuthenticationModel

    @GET("users")
    suspend fun getUserData(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<UserDataResponse>

}



