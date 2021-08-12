package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.*
import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etname2: EditText
    lateinit var etDob: EditText
    lateinit var spNationality: Spinner
    lateinit var etPassword: EditText
    lateinit var etPhoneNumber: EditText
    lateinit var etEmail: EditText
    lateinit var btnRegister: Button
    lateinit var pbRegistration:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castView()
        clickRegister()
    }

    fun castView(){
         etname2=findViewById(R.id.etname1)
         etDob=findViewById(R.id.etDob)
         spNationality=findViewById(R.id.spNationality)
         etPassword=findViewById(R.id.etPassword)
         etPhoneNumber=findViewById(R.id.etPhoneNumber)
         etEmail=findViewById(R.id.etEmail)
         btnRegister=findViewById(R.id.btnRegister)

        var nationalities= arrayOf("Kenyan","Rwandan","South Sudanese","Sudanese","Ugandan")
        var nationalitiesAdapter=ArrayAdapter(baseContext,android.R.layout.simple_spinner_item,nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNationality.adapter=nationalitiesAdapter


        }
    fun clickRegister(){
        btnRegister.setOnClickListener {
            pbRegistration.visibility=View.VISIBLE
            var name=etname2.text.toString()
            if(name.isEmpty()){
                etname2.setError("Name is required")
                val intent = Intent(baseContext,CoursesActivity::class.java)
                startActivity(intent)
            }
            var dob=etDob.text.toString()
            if(dob.isEmpty()){
                etDob.setError("Enter DOB")
            }
            var nationality=spNationality.selectedItem.toString().toUpperCase()
            var password=etPassword.text.toString().toString()
            if(password.isEmpty()){
                etPassword.setError("Enter password")
            }
            var phone=etPhoneNumber.text.toString()
            if(phone.isEmpty()){
                etPhoneNumber.setError("Enter Phone Number")
            }
            var email=etEmail.text.toString()
            if(name.isEmpty()){
                etEmail.setError("required")
            }
            var regRequest=RegistrationRequest(
                name=name,phoneNumber=phone,email=email,dateOfBirth = dob,nationality = nationality,password = password
            )
            var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
            var request=retrofit.registerStudent(regRequest)
            request.enqueue(
                object : Callback<RegistrationResponse> {
                    override fun onResponse(call: retrofit2.Call<RegistrationResponse>, response: Response<RegistrationResponse>, ) {
                        pbRegistration.visibility=View.GONE
                        if (response.isSuccessful) {
                            Toast.makeText(
                                baseContext,
                                "Registration successful",
                                Toast.LENGTH_LONG,
                            ).show()
                        } else {
                            Toast.makeText(
                                baseContext,
                                response.errorBody()?.toString(),
                                Toast.LENGTH_LONG,
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: retrofit2.Call<RegistrationResponse>,
                        t: Throwable,
                    ) {
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                        pbRegistration.visibility=View.GONE
                    }})
        }}}
data class Info(
    var name: String,
    var password: String,
    var email: String,
    var nationality: String,
    var phone: String
)