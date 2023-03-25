package com.example.zeeshanassignmentmsd.my

interface UserRepository {
    suspend fun getAllUsers(): ApiResponse<List<User>>

    suspend fun getUserById(id: Int): ApiResponse<User>
}