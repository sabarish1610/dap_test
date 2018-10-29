package `in`.medhos.find.doctors.common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * Created by Viji on 02-03-2018.
 */
class PreferenceHelper(private val context: Context) {
    val emailId = "EMAIL ID"
    val isLoggedIN = "IS LOGGED IN"
    val role = "role"
    var preferences: SharedPreferences? = null

    init {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setStringValue(key: String, value: String) {
        if (preferences != null) {
            val editor = preferences!!.edit()
            editor.putString(key, value)
            editor.commit()
        }
    }
    fun clearShared()
    {
        if (preferences != null) {
            val editor = preferences!!.edit()
            editor.clear()
            editor.apply()
        }
    }


    fun setBooleanValue(key: String, value: Boolean) {
        if (preferences != null) {
            val editor = preferences!!.edit()
            editor.putBoolean(key, value)
            editor.commit()
        }
    }

    fun getStringValue(key: String): String? {
        var value: String? = null
        if (preferences != null) {
            value = preferences!!.getString(key, "")
        }
        return value
    }

    fun getBooleanValue(key: String): Boolean? {
        var value: Boolean? = false
        if (preferences != null) {
            value = preferences!!.getBoolean(key, false)
        }
        return value
    }
}