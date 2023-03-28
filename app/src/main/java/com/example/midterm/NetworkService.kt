package com.example.midterm


class NetworkService {
    private val retrofit by lazy { RetrofitHelper.getInstance() }
    suspend fun loadUsersList(): List<User> {
        val apiService = retrofit.create(UserApi::class.java)
        return apiService.getUsers().run {
            this.body() ?: throw Exception("user not found")
        }
    }
}