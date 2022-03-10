package com.example.birthdaytracker

import android.util.Patterns

object RegistrationUtil {
    var existingUsers = listOf("cosmicF", "cosmicY", "superman")
    var existingEmails = listOf("cosmicF@gmail.com", "cosmicY@gmail.com", "clarkkent@gmail.com")

    // isn't empty
    fun validateName(name : String) : Boolean {
        return name.isNotBlank()
    }

    // isn't already used, min char count of 3, isn't empty
    fun validateUsername(usr : String) : Boolean {
        return usr !in existingUsers && usr.length >= 3 && !usr.contains(" ")
    }

    // make sure it meets security requirements (deprecated), both pws match, and neither are empty
    // at least 8 chars
    // at least 1 digit
    // at least 1 special char (not doing this per instruction)
    // at least 1 capital letter
    fun validatePassword(pw : String, confirmPw : String) : Boolean {
        if (pw != confirmPw || pw.length < 8 || pw.lowercase() == pw || pw.contains(" ")) {
            return false
        }
        val lambda: (String) -> Boolean = {"0" <= it && it <= "9"} // returns true if input is a number between 0 and 9
        // for every char in pw, if lambda returns true count increments its return value by 1
        // if .count(lambda) returns 0, there are no digits in the string and therefore it is invalid
        if (pw.split("").count(lambda) == 0) return false
        return true
    }

    // make sure it isn't empty, isn't already used, and is in proper format (user@domain.tld)
    fun validateEmail(email : String) : Boolean {
        return email.isValidEmail() && email !in existingEmails
    }

    private fun String.isValidEmail() : Boolean {
        return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}