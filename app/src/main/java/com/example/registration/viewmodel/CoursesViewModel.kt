package com.example.registration.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.repository.CoursesRepository
import com.example.registration.models.CoursesResponse
import kotlinx.coroutines.launch

class CoursesViewModel: ViewModel() {
    var coursesRepository = CoursesRepository()
    var courseResponseLiveData = MutableLiveData<List<CoursesResponse>>()
    var courseErrorLiveData = MutableLiveData<String>()

    fun displayCoursesList(accessToken: String) {
        viewModelScope.launch {
            var response = coursesRepository.coursesList(accessToken)
            if (response.isSuccessful) {
                Log.e("CoursesViewModel", ": displayCoursesList: response.body[ ${response.body().toString()} ]")
                //courseResponseLiveData.postValue(response.body())
            } else {
                courseErrorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }
}