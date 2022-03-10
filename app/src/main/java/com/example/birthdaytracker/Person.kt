package com.example.birthdaytracker

import java.util.*

// Backendless and other online BooS (backend as a service)
// apis often require model classs to have a
// default, no parameter constructor
// So, in Kotlin, you just give each field a default value
// That way you can have a no parameter constructor
data class Person(
    var name : String = "Dan",
    var birthday : Date = Date(0),
    var budget : Int = 10,
    var desiredGift : String = "Nothing",
    var previousGifts : List<String> = listOf(),
    var previousGiftsToMe : List<String> = listOf(),
    var giftPurchased : Boolean = false

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
) {
    fun getAge() : Int {
        val ageMillis = System.currentTimeMillis() - this.birthday.time
        // ageMillis * 1s/1ms * 1m/60s * 1hr/60m * 1d/24hr * 1yr/365d -> age
        return (ageMillis / 1000 / 60 / 60 / 24 / 365).toInt()
    }

    fun getDaysUntilBirthday() : Int {

        return 0
    }
}
