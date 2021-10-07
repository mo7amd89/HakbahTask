package com.hakbah.task.db

interface HakbahDbRepository {

    suspend fun getPosts(): List<Post>

    suspend fun addPost(post: Post): Long

    suspend fun deletePost(post: Post)
}