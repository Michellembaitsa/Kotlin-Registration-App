package com.example.registration.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.Repository.LoginRepository
import com.example.registration.Repository.UserRepository
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    var userRepository = UserRepository()
    var regErrorLiveData = MutableLiveData<String>()
    var regResponseLivedata = MutableLiveData<RegistrationResponse>()
    var loginRepository = LoginRepository()
    var logErrorLiveData = MutableLiveData<String>()
    var logResponseLiveData = MutableLiveData<LogInResponse>()


    fun registerStudent(registrationRequest: RegistrationRequest) {
        viewModelScope.launch {
            var response = userRepository.registerUser(registrationRequest)
            if (response.isSuccessful) {
                regResponseLivedata.postValue(response.body())
            } else {
                regErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
        fun logInStudent(logInRequest: LogInRequest) {
            viewModelScope.launch {
                var response = loginRepository.loginUser(logInRequest)
                if (response.isSuccessful) {
                  logResponseLiveData.postValue(response.body())
                } else {
                  logErrorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
    }

