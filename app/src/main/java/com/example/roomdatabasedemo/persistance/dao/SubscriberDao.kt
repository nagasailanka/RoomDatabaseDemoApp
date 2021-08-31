package com.example.roomdatabasedemo.persistance.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabasedemo.persistance.entity.Subscriber

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insert(subscriber: Subscriber)

    @Update
    suspend fun update(subscriber: Subscriber)

    @Delete
    suspend fun delete(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_table")
    suspend fun deleteAll()

    @Query(" SELECT * FROM subscriber_table")
    fun getAllSubscribers() : LiveData<List<Subscriber>>


}