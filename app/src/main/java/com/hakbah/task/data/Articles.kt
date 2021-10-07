package com.hakbah.task.data


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("articles")
    val articles: List<Articles>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    data class Articles(
        @SerializedName("author")
        val author: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("source")
        val source: Source,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("urlToImage")
        val urlToImage: String
    ) {
        data class Source(
            @SerializedName("id")
            val id: Any,
            @SerializedName("name")
            val name: String
        )
    }
}