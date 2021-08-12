package com.example.registration.api

import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
   suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

    @POST("/student/login")
    fun loginStudent(@Body logInRequest: LogInRequest):Call<LogInResponse>
}