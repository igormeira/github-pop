package com.igormeira.githubpop.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtil {

    companion object {

        private fun getLoginSharedPref(context: Context) : SharedPreferences {
            val privateMode = 0
            return context.getSharedPreferences(Constants.LOGIN_PREFS.name, privateMode)
        }

        fun saveId(context: Context, token: String) {
            val sharedPreferences = getLoginSharedPref(context)
            val editor = sharedPreferences.edit()
            editor.putString(Constants.ID.name, token)
            editor.apply()
        }

        fun saveUsername(context: Context, token: String) {
            val sharedPreferences = getLoginSharedPref(context)
            val editor = sharedPreferences.edit()
            editor.putString(Constants.USERNAME.name, token)
            editor.apply()
        }

        fun getId(context: Context) : String? {
            val sharedPreferences = getLoginSharedPref(context)
            return sharedPreferences.getString(Constants.ID.name, "")
        }

        fun getUsername(context: Context) : String? {
            val sharedPreferences = getLoginSharedPref(context)
            return sharedPreferences.getString(Constants.USERNAME.name, "")
        }

        fun logout(context: Context) {
            val sharedPreferences = getLoginSharedPref(context)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }

    }

}