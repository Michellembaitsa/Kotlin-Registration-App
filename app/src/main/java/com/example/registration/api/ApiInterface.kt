package com.example.registration.api

import com.example.registration.models.*
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
   suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

    @POST("/student/login")
    fun loginStudent(@Body logInRequest: LogInRequest):Response<LogInResponse>

    @POST("/courses")
    suspend fun fetchCourses(@Header("Authorization")token:String):Response<CoursesResponse>

}