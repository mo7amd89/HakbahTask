package com.hakbah.task.data

import com.hakbah.task.db.Post

interface PostOnClickListener {
    fun deletePost(post: Post)
}