package com.hakbah.task.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakbah.task.databinding.PostItemsBinding
import com.hakbah.task.db.Post
import java.io.File

class PostAdapter(
    private val postOnClickListener: PostOnClickListener
) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    private var posts = emptyList<Post>()

    fun submitList(current: List<Post>) {
        this.posts = current

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val binding = PostItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.bind(posts[position], postOnClickListener)

    }

    override fun getItemCount() = posts.size

    class PostHolder(private val binding: PostItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post, postOnClickListener: PostOnClickListener) {
            binding.apply {
                Glide.with(itemView)
                    .load(File(post.image))
                    .into(imageViewLogo)

                textViewAuthor.text = post.name
                textViewDescription.text = post.description
                textViewPublishedAt.text = post.time

                imageViewDelete.setOnClickListener {
                    postOnClickListener.deletePost(post)
                }
            }


        }
    }
}