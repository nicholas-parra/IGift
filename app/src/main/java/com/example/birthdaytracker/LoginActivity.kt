package com.example.birthdaytracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.birthdaytracker.databinding.ActivityLoginBinding
import java.sql.Date
import java.util.*


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    // class variables in java are called companion objects in kotlin
    // you can access one with ClassName.VARIABLE_NAME, like Math.PI in java
    companion object {
        val TAG = "LoginActivity"
        val EXTRA_USERNAME = "username" // the key doesn't really matter here
        val EXTRA_PASSWORD = "password"
    }

    private val startRegistrationForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // you have to call setText on EditText views
            binding.editTextLoginUsername.setText(intent?.getStringExtra(EXTRA_USERNAME))
            binding.editTextLoginPassword.setText(intent?.getStringExtra(EXTRA_PASSWORD))
        } else if (result.resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Registration was cancelled due to invalid information entered.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val person = Person()
        Log.d(TAG, "onCreate: ${person.getAge()}")

        binding.textViewLoginCreateAccount.setOnClickListener {
            // go to registration activity
            val username = binding.editTextLoginUsername.text.toString()
            val password = binding.editTextLoginPassword.text.toString()
            // intent = Intent(CurrentClass, DestinationClass).apply { other stuff }
            val registrationIntent = Intent(this, RegistrationActivity::class.java).apply {
                putExtra(EXTRA_USERNAME, username)
                putExtra(EXTRA_PASSWORD, password)
            }
            // launch the activity normally
            // startActivity(intent)

            // launch the activity for a result
            startRegistrationForResult.launch(registrationIntent)
        }

        binding.buttonLoginLogin.setOnClickListener {

        }
    }
}

/*
    /* VARIABLES
    To remember
    Name: String
    Bday: Date or String
    Budget: Double or Int
    Preferred gift: String
    What you got them before: List<String>
    What they got you before: List<String>
    Gift Purchased?: Boolean

    Derived:
    Age (from bday): Int
    Days till bday (from bday): Int
     */
*/