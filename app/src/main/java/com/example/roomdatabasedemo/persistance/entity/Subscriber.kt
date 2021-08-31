package com.example.roomdatabasedemo.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,
)
