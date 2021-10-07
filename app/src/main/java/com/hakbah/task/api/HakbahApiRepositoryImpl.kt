package com.hakbah.task.api

import com.hakbah.task.data.Article
import javax.inject.Inject

class HakbahApiRepositoryImpl @Inject constructor(
    private val api: HakbahApi) : HakbahApiRepository {

    override suspend fun getArticles(): Article =api.getArticles()
}