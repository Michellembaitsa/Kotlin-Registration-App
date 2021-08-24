package com.example.registration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.example.registration.ViewModel.UserViewModel
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.RegistrationRequest

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    lateinit var sharedPrefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE)
        setupSpinner()
        clickRegister()

    }
    fun redirectUser(){
        var token=sharedPrefs.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        if (token!!.isNotEmpty()){
            startActivity(Intent())

        }

}
    fun setupSpinner() {
        var nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Sudanese", "Ugandan")
        var nationalitiesAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.etNationality.adap = nationalitiesAdapter
    }

    fun clickRegister() {
        binding.btnRegister.setOnClickListener {
            binding.pbRegistration.visibility = View.VISIBLE
            var name = binding.etName.text.toString()
            if (name.isEmpty()) {
                binding.etName.setError("Name is required")
                val intent = Intent(baseContext, CoursesActivity::class.java)
                startActivity(intent)
            }
            var dob = binding.etDob.text.toString()
            if (dob.isEmpty()) {
                binding.etDob.setError("Enter DOB")
            }
//            var nationality = binding.etNationality.selectedItem.toString().uppercase()
//            var password = binding.etP.text.toString().toString()
//            if (password.isEmpty()) {
//                binding.etPassword.setError("Enter password")
//            }
            var phone = binding.etPhoneNumber.text.toString()
            if (phone.isEmpty()) {
                binding.etPhoneNumber.setError("Enter Phone Number")
            }
            var email = binding.etEmail.text.toString()
            if (name.isEmpty()) {
                binding.etEmail.setError("required")
            }
            var regRequest = RegistrationRequest(
                name = name,
                phoneNumber = phone,
                email = email,
                dateOfBirth = dob,
//                nationality = nationality,
//                password = password
            )
            userViewModel.registerStudent(regRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.regResponseLivedata.observe(this, { regResponse ->
            binding.pbRegistration.visibility= View.GONE
            if (!regResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "registration successful", Toast.LENGTH_LONG).show()
            }
        })
        binding.pbRegistration.visibility= View.GONE
        userViewModel.regErrorLiveData.observe(this, {
            Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()

        )}
//
//    data class Info(
//        var name: String,
//        var password: String,
//        var email: String,
//        var nationality: String,
//        var phone: String
//    )
}}