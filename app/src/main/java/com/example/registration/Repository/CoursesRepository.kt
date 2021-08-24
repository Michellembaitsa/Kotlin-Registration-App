package com.example.registration.Repository

import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.models.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
    var coursesInterface=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun coursesList(accessToken:String): Response<List<CoursesResponse>> =
        withContext(Dispatchers.IO){
            var response=coursesInterface.fetchCourses(accessToken)
            return@withContext response
        }

}