package com.example.registration.repository

import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(logInRequest: LogInRequest): Response<LogInResponse> =
        withContext(Dispatchers.IO){
            var response = retrofit.loginStudent(logInRequest)
            return@withContext response

        }
}