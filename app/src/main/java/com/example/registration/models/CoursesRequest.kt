package com.example.registration.models

import com.google.gson.annotations.SerializedName

data class CoursesRequest(
    val course_name:String,
    val course_code:String,
    val description:String,
    val instructor:String,
)
