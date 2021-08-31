/*
 * Copyright (c) 2020 Shadkona
 */

package com.example.roomdatabasedemo

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.roomdatabasedemo.constans.AppConstants
import com.example.roomdatabasedemo.persistance.AppDatabase
import com.preference.PowerPreference


class ApplicationClass : Application() {

    companion object {
        lateinit var INSTANCE: Application

        var database: AppDatabase? = null

        /**
         * @return The singleton instance
         */
        fun getApp(): Application {
            return INSTANCE
        }

        fun getAppContext(): Context {
            return INSTANCE
        }
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this


        PowerPreference.init(this)
        // Database initilization

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            AppConstants.DB_NAME
        ).build()

        PowerPreference.init(this)
    }


}