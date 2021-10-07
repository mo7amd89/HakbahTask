package com.hakbah.task.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hakbah.task.R
import com.hakbah.task.data.Article
import com.hakbah.task.data.ArticleAdapter
import com.hakbah.task.databinding.FragmentHomeBinding
import com.hakbah.task.data.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


    private val viewModel:HomeViewModel by viewModels()


    private var binding: FragmentHomeBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding=FragmentHomeBinding.bind(view)
        binding=fragmentHomeBinding

        getArticles()

    }

   private fun getArticles(){

        viewModel.getArticles().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING -> {
                    binding!!.progressBar.visibility=View.VISIBLE
                }
                Status.ERROR -> {
                    binding!!.progressBar.visibility=View.GONE
                }
                Status.SUCCESS -> {
                    binding!!.progressBar.visibility=View.GONE
                    showArticles(it.data!!.articles)
                }
            }
        })
    }

    private fun showArticles(articles: List<Article.Articles>) {

        val articleAdapter=ArticleAdapter()

        binding!!.recyclerView.apply {
             adapter=articleAdapter
        }

        articleAdapter.submitList(articles)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}