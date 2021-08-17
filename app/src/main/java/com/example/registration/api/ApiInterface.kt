package com.example.registration.api

<<<<<<< HEAD
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
=======
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
>>>>>>> origin/main
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
<<<<<<< HEAD
   suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

    @POST("/student/login")
    fun loginStudent(@Body logInRequest: LogInRequest):Call<LogInResponse>
=======
    fun registerStudent(@Body registrationRequest: RegistrationRequest):Call<RegistrationResponse>
>>>>>>> origin/main
}