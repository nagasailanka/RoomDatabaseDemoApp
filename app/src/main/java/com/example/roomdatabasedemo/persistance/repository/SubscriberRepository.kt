package com.example.roomdatabasedemo.persistance.repository

import com.example.roomdatabasedemo.persistance.dao.SubscriberDao
import com.example.roomdatabasedemo.persistance.entity.Subscriber

class SubscriberRepository(private val dao: SubscriberDao) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) {
        dao.insert(subscriber)
    }

    suspend fun update(subscriber: Subscriber) {
        dao.update(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) {
        dao.delete(subscriber)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}