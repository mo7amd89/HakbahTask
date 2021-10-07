package com.hakbah.task.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hakbah.task.databinding.ArticleItemsBinding
import com.hakbah.task.databinding.PostItemsBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {
    private var articles= emptyList<Article.Articles>()

    fun submitList(current :List<Article.Articles>){
        this.articles=current
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {

        val binding=ArticleItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {

            holder.bind(articles[position])


        //
    }

    override fun getItemCount()=articles.size

    class ArticleHolder(private val binding : ArticleItemsBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(article :Article.Articles){
        binding.apply {
            Glide.with(itemView)
                .load(article.urlToImage)
                .into(imageViewLogo)

            textViewAuthor.text=article.source.name
            textViewDescription.text=article.title
            textViewPublishedAt.text=article.publishedAt
        }
    }
    }
}