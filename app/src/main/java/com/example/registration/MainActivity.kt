package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etDob: EditText
    lateinit var etNationality: EditText
    lateinit var etId: EditText
    lateinit var etPhoneNumber: EditText
    lateinit var etEmail: EditText
    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castView()
        clickRegister()

    }
    fun castView(){
         etName=findViewById(R.id.etName)
         etDob=findViewById(R.id.etDob)
         etNationality=findViewById(R.id.etNationality)
         etId=findViewById(R.id.etId)
         etPhoneNumber=findViewById(R.id.etPhoneNumber)
         etEmail=findViewById(R.id.etEmail)
         btnRegister=findViewById(R.id.btnRegister)
//        clickRegister()
        }
    fun clickRegister(){
        btnRegister.setOnClickListener { btnRegisterView ->

            etName.text.toString().let {
                if (it.isNullOrBlank()) {
                    etName.error = "Name is required"
                    //startActivity(Intent(baseContext, CoursesActivity::class.java))
                }
                return@let it
            }

            etDob.text.toString().let {
                if (it.isNullOrBlank()) {
                    etNationality.error = "Date of birth is required"
                }
            }

            var nationality=etNationality.text.toString()
            if(nationality.isEmpty()){
                etId.getError()
            }
            var id=etId.text.toString().toString()
            if(id.isEmpty()){
                etPhoneNumber.getError()
            }
            var phone=etPhoneNumber.text.toString()
            if(phone.isEmpty()){
                etEmail.getError()
            }
            var email=etEmail.text.toString()

            //var info=Info(name, id, email, nationality, phone)

        }
    }
}
data class Info(
    var name: String,
    var id: String,
    var email: String,
    var nationality: String,
    var phone: String,
)