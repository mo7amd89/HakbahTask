package com.hakbah.task.db

import javax.inject.Inject

class HakbahDbRepositoryImpl @Inject constructor(
    private val dataBase: HakbahDataBase
) :HakbahDbRepository{
    override suspend fun getPosts(): List<Post> =dataBase.hakbahDao().getPosts()

    override suspend fun addPost(post: Post): Long =dataBase.hakbahDao().addPost(post)

    override suspend fun deletePost(post: Post) =dataBase.hakbahDao().deletePost(post)
}