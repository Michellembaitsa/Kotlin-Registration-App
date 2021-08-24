package com.example.registration.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration.ViewModel.UserViewModel
import com.example.registration.databinding.ActivityLogInBinding
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse

class LogIn : AppCompatActivity(){
    lateinit var binding: ActivityLogInBinding
    val userViewModel: UserViewModel by viewModels()
    lateinit var sharedPrefs:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
    setContentView(binding.root)
        sharedPrefs=getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE)
        logInStudent()

}
//fun castViews(){
//    binding.tilusername=findViewById(R.id.tilusername)
//    binding.tilpassword=findViewById(R.id.tilpassword)
//    binding.btnlogin=findViewById(R.id.btnlogin)
//}
fun logInStudent(){
    var email=binding.tilusername.text.toString()
    var pswd=binding.tilpassword.text.toString()
    var error=false
    binding.btnlogin.setOnClickListener {
        if (email.isEmpty()){
            binding.tilusername.setError("This field is compulsory")
        }
        if (pswd.isEmpty()){
            binding.tilpassword.setError("This field is compulsory")
        }
    }
    val loginRequest=LogInRequest(
        email=email,password = pswd
    )

    userViewModel.logInStudent(loginRequest)

}
    override fun onResume() {
        super.onResume()
        binding.btnlogin.setOnClickListener {
//            validatelogin()
            binding.tvError.visibility=View.GONE
        }
        userViewModel.logResponseLiveData.observe(this,{ logInResponse ->
            var editor=sharedPrefs.edit()
            editor.putString(Constants.ACCESS_TOKEN,logInResponse.accessToken)
            editor.putString(Constants.STUDENT_ID,logInResponse.studentId)
            editor.apply()

//            binding.pb
            Toast.makeText(baseContext,"Your login was successful",Toast.LENGTH_LONG).show()
        })
        userViewModel.logErrorLiveData.observe(this, { loginRequest ->
            Toast.makeText(baseContext, "Your login was not successful", Toast.LENGTH_LONG).show()
        })
    }
}
