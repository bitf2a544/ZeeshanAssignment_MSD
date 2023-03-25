package com.example.zeeshanassignmentmsd.my

import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_USER_PATH_URL = "/aqua30/users"

interface TestApis {

    @GET(BASE_USER_PATH_URL)
    suspend fun getAllUsers(): List<User>

    @GET("$BASE_USER_PATH_URL/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}