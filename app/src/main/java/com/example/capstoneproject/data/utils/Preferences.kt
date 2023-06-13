package com.example.capstoneproject.data.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val tagStatus = "status"
    private val tagLevel = "level"
    private val tagApp = "app"

    private val pref: SharedPreferences =
        context.getSharedPreferences(tagApp, Context.MODE_PRIVATE)

    var prefStatus: Boolean
        get() = pref.getBoolean(tagStatus, false)
        set(value) = pref.edit().putBoolean(tagStatus, value).apply()

    var prefName: String?
        get() = pref.getString(tagLevel, "")
        set(value) = pref.edit().putString(tagLevel, value).apply()

    fun prefClear() {
        pref.edit().remove(tagStatus).apply()
        pref.edit().remove(tagLevel).apply()
    }
}