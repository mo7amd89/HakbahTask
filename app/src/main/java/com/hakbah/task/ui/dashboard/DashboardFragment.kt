package com.hakbah.task.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hakbah.task.R
import com.hakbah.task.data.*
import com.hakbah.task.databinding.FragmentDashboardBinding
import com.hakbah.task.db.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) , PostOnClickListener {

    private val viewModel: DashboardViewModel by viewModels()
    private var binding: FragmentDashboardBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentDashboardBinding = FragmentDashboardBinding.bind(view)
        binding = fragmentDashboardBinding


        binding!!.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_dashboard_to_addPostFragment)
        }

        getPosts()
    }



    private fun getPosts(){

        viewModel.getPosts().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
                Status.SUCCESS -> {

                    showPosts(it.data!!)
                }
            }
        })
    }

    private fun showPosts(posts: List<Post>) {

        val postAdapter= PostAdapter(this)

        binding!!.recyclerView.apply {
            adapter=postAdapter
        }

        postAdapter.submitList(posts)
    }

    override fun deletePost(post: Post) {

        viewModel.deletePost(post).observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
                Status.SUCCESS -> {

                   getPosts()
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}