package com.it.myprojectfinal.rest.local

import android.content.Context
import android.content.SharedPreferences


class Preferrences(private var context: Context) {

    companion object {

        const val FILENAME = "app_project"
        const val TOKEN = "token"
        const val USER_ID = "user_id"
    }

    fun getToken(): String {
        return getString(TOKEN) ?: ""
    }

    fun getUserId(): String? {
        return getString(USER_ID)
    }

    private fun getString(key: String): String? {
        return getShareadPreferrneces().getString(key, null)
    }

    private fun getInt(key: String): Int? {
        return getShareadPreferrneces().getInt(key, 0)
    }

    fun cleardatalogout() {
        saveString(TOKEN, "")
    }

    internal fun clear() {
        getShareadPreferrneces().edit().clear().apply()
    }

    fun saveToken(token: String) {
        saveString(TOKEN, token)
    }

    fun saveUserId(user_id: String) {
        saveString(USER_ID, user_id)
    }

    private fun saveString(key: String, value: String) {
        val editor = getShareadPreferrneces().edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun saveInt(key: String, value: Int) {
        val editor = getShareadPreferrneces().edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun getShareadPreferrneces(): SharedPreferences {

        return context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

    }

}