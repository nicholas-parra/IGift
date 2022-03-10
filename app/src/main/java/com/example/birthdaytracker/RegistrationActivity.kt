package com.example.birthdaytracker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.birthdaytracker.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sets username and password in registration layout to whatever was in login layout
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME)
        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD)
        binding.editTextRegistrationUsername.setText(username.toString())
        binding.editTextRegistrationPassword.setText(password.toString())

        binding.buttonRegistrationRegister.setOnClickListener {
            // TODO: verify that information entered is valid

            // In a real app, we talk to a database and save the user in the database first

            // Now, we return to the login screen and send back usr/pw to prefill
            val name = binding.editTextTextRegistrationName.text.toString()
            val usr = binding.editTextRegistrationUsername.text.toString()
            val pw = binding.editTextRegistrationPassword.text.toString()
            val confirmpw = binding.editTextRegistraionConfirmPassword.text.toString()
            val email = binding.editTextRegistrationEmail.text.toString()

            if (!RegistrationUtil.validateName(name)) {
                Toast.makeText(this, "Invalid name!", Toast.LENGTH_SHORT).show()
            } else if (!RegistrationUtil.validateUsername(usr)) {
                Toast.makeText(this, "Invalid username!", Toast.LENGTH_SHORT).show()
            } else if (!RegistrationUtil.validatePassword(pw, confirmpw)) {
                Toast.makeText(this, "Invalid passwords and/or passwords did not match!", Toast.LENGTH_SHORT).show()
            } else if (!RegistrationUtil.validateEmail(email)) {
                Toast.makeText(this, "Invalid email!", Toast.LENGTH_SHORT).show()
            } else {
                val returnToLoginIntent =
                    Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri")).apply {
                        putExtra(LoginActivity.EXTRA_USERNAME, usr)
                        putExtra(LoginActivity.EXTRA_PASSWORD, pw)
                    }
                if (usr.isEmpty() || pw.isEmpty()) {
                    setResult(Activity.RESULT_CANCELED, returnToLoginIntent)
                } else {
                    setResult(Activity.RESULT_OK, returnToLoginIntent)
                }
                finish() // closes activity
            }
        }
    }
}