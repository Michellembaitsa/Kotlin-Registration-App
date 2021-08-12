package com.example.registration.api

import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest):Call<RegistrationResponse>
}