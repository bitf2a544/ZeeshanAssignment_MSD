package com.example.zeeshanassignmentmsd.my


import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl(
    private val testApis: TestApis
): UserRepository {

    override suspend fun getAllUsers(): ApiResponse<List<User>> {
        return try {
            ApiResponse(
                body = testApis.getAllUsers()
            )
        } catch (e: HttpException) {
            ApiResponse(
                httpCode = e.code(),
                errorMessage = "server error"
            )
        } catch (e: IOException) {
            ApiResponse(
                errorMessage = "connection error"
            )
        }
    }

    override suspend fun getUserById(id: Int): ApiResponse<User> {
        return try {
            ApiResponse(
                body = testApis.getUserById(id)
            )
        } catch (e: HttpException) {
            ApiResponse(
                httpCode = e.code(),
                errorMessage = "server error"
            )
        } catch (e: IOException) {
            ApiResponse(
                errorMessage = "connection error"
            )
        }
    }
}