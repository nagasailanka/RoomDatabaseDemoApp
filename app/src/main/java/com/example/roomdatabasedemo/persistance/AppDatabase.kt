package com.example.roomdatabasedemo.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabasedemo.constans.AppConstants
import com.example.roomdatabasedemo.persistance.dao.SubscriberDao
import com.example.roomdatabasedemo.persistance.entity.Subscriber

@Database(entities = [Subscriber::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun subscriberDao(): SubscriberDao

    companion object {

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            synchronized(this){
                var instance: AppDatabase? = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        AppConstants.DB_NAME
                    ).build()
                }

                return instance
            }

        }

    }

}