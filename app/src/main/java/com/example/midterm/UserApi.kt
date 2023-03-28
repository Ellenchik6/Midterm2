package com.example.midterm

import retrofit2.Response
import retrofit2.http.GET


interface UserApi {

    @GET("api/?inc=nat,name,email&results=100")
    suspend fun getUsers(): Response<List<User>>

}