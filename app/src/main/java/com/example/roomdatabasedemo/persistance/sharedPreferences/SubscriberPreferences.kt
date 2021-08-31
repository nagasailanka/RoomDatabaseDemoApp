package com.example.roomdatabasedemo.persistance.sharedPreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.roomdatabasedemo.ApplicationClass

class SubscriberPreferences private constructor(context: Context) {
    private var subscriberPrefsBulkUpdate: Boolean = false



    enum class Key {
        UPDATE_OR_DELETE_STATUS, ID,
        IS_UPDATE,IS_SAVE
    }

    companion object {
        private const val SETTINGS_NAME = "default_settings"

        private var kolagaramPreferences: SubscriberPreferences? = null
        private var sharedPrefs: SharedPreferences? = null
        private var sharedPrefsEditor: SharedPreferences.Editor? = null

        fun getInstance(context: Context): SubscriberPreferences? {
            if (kolagaramPreferences == null) {
                kolagaramPreferences = SubscriberPreferences(context.applicationContext)
            }

            return kolagaramPreferences
        }

        fun getInstance(): SubscriberPreferences? {
            if (kolagaramPreferences != null) {
                return kolagaramPreferences
            }

            return getInstance(ApplicationClass.getAppContext())
        }
    }

    init {
        sharedPrefs = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        doEdit()
        sharedPrefsEditor?.clear()
        doCommit()
    }

    @SuppressLint("CommitPrefEdits")
    fun edit() {
        subscriberPrefsBulkUpdate = true
        sharedPrefsEditor = sharedPrefs?.edit()
    }

    fun commit() {
        subscriberPrefsBulkUpdate = false
        sharedPrefsEditor?.commit()
        sharedPrefsEditor = null
    }

    @SuppressLint("CommitPrefEdits")
    private fun doEdit() {
        if (!subscriberPrefsBulkUpdate && sharedPrefsEditor == null) {
            sharedPrefsEditor = sharedPrefs?.edit()
        }
    }

    private fun doCommit() {
        if (!subscriberPrefsBulkUpdate && sharedPrefsEditor != null) {
            sharedPrefsEditor?.commit()
            sharedPrefsEditor = null
        }
    }

    fun remove(vararg keys: Key) {
        doEdit()
        for (key in keys) {
            sharedPrefsEditor?.remove(key.name)
        }

        doCommit()
    }

    fun put(key: Key, value: String?) {
        doEdit()
        sharedPrefsEditor?.putString(key.name, value)
        doCommit()
    }

    fun put(key: Key, value: Int) {
        doEdit()
        sharedPrefsEditor?.putInt(key.name, value)
        doCommit()
    }

    fun put(key: Key, value: Boolean) {
        doEdit()
        sharedPrefsEditor?.putBoolean(key.name, value)
        doCommit()
    }

    fun put(key: Key, value: Float) {
        doEdit()
        sharedPrefsEditor?.putFloat(key.name, value)
        doCommit()
    }

    fun put(key: Key, value: Double) {
        doEdit()
        sharedPrefsEditor?.putString(key.name, value.toString())
        doCommit()
    }

    fun put(key: Key, value: Long) {
        doEdit()
        sharedPrefsEditor?.putLong(key.name, value)
        doCommit()
    }

    fun getString(key: Key, defaultValue: String?): String? {
        return sharedPrefs?.getString(key.name, defaultValue)
    }

    fun getString(key: Key): String? {
        return sharedPrefs?.getString(key.name, null)
    }

    fun getInt(key: Key): Int? {
        return sharedPrefs?.getInt(key.name, 0)
    }

    fun getInt(key: Key, defaultValue: Int): Int? {
        return sharedPrefs?.getInt(key.name, defaultValue)
    }

    fun getLong(key: Key): Long? {
        return sharedPrefs?.getLong(key.name, 0)
    }

    fun getLong(key: Key, defaultValue: Long): Long? {
        return sharedPrefs?.getLong(key.name, defaultValue)
    }

    fun getFloat(key: Key): Float? {
        return sharedPrefs?.getFloat(key.name, 0f)
    }

    fun getFloat(key: Key, defaultValue: Float): Float? {
        return sharedPrefs?.getFloat(key.name, defaultValue)
    }

    fun getDouble(key: Key): Double {
        return getDouble(key, 0.0)
    }

    fun getDouble(key: Key, defaultValue: Double): Double {
        return try {
            java.lang.Double.valueOf(
                sharedPrefs?.getString(
                    key.name,
                    defaultValue.toString()
                ) as String
            )
        } catch (nfe: NumberFormatException) {
            defaultValue
        }
    }

    fun getBoolean(key: Key, defaultValue: Boolean): Boolean? {
        return sharedPrefs?.getBoolean(key.name, defaultValue)
    }

    fun getBoolean(key: Key): Boolean? {
        return sharedPrefs?.getBoolean(key.name, false)
    }
}