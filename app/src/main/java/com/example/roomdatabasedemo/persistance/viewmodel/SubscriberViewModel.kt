package com.example.roomdatabasedemo.persistance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasedemo.persistance.entity.Subscriber
import com.example.roomdatabasedemo.persistance.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscribers = repository.subscribers

    fun insert(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.update(subscriber)
        }
    }

    fun delete(subscriber: Subscriber) {
        viewModelScope.launch {
            repository.delete(subscriber)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }


}