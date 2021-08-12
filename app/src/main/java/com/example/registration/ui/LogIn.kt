package com.example.registration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import com.example.registration.R
import com.example.registration.ViewModel.UserViewModel
import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.databinding.ActivityLogInBinding
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.LogInRequest
import com.example.registration.models.LogInResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogIn : AppCompatActivity(){
    lateinit var binding: ActivityLogInBinding
    val userViewModel: UserViewModel by viewModels()

//lateinit var logintoolbar: Toolbar
//lateinit var tilusername:EditText
//lateinit var tilpassword:EditText
//lateinit var btnlogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
    setContentView(binding.root)
//        castViews()
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
//
    userViewModel.logInStudent(loginRequest)

}
    override fun onResume() {
        super.onResume()
        userViewModel.logResponseLiveData(this,{ loginResponse ->
            Toast.makeText(baseContext,"Your login was succesful",Toast.LENGTH_LONG).show()
        })
        userViewModel.logErrorLiveData.observe(this, { loginRequest ->
            Toast.makeText(baseContext, "Your login was not ccessful", Toast.LENGTH_LONG).show()
        })
    }


}
