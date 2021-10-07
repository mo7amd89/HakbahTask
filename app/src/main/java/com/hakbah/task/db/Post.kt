package com.hakbah.task.db

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "posts")
data class Post(
    val name: String,
    val image: String,
    @PrimaryKey val time: String,
    val description: String
)
