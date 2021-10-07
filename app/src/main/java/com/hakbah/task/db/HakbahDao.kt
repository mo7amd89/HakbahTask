package com.hakbah.task.db

import androidx.room.*

@Dao
interface HakbahDao {

    @Query("SELECT * FROM posts")
    suspend fun getPosts() :List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(post: Post): Long

    @Delete
    suspend fun deletePost(post: Post)

}