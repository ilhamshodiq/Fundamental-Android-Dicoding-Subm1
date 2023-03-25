package com.The12sMB.fundamentalsubmission1letsgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.The12sMB.fundamentalsubmission1letsgo.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowerBinding.bind(view)
        val followerViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)

        val login = arguments?.getString(ARG_LOGIN)
        followerViewModel.getDetailFollower(login.toString())
        followerViewModel.githubuserfollower.observe(viewLifecycleOwner) { items ->
            binding.rvFollower.adapter = showRecyclerView(items)
        }

        followerViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun showRecyclerView(list: List<GithubUserResponse>?): MainMenuAdapter {
        val userList = ArrayList<GithubUserResponse>()

        list?.let {
            userList.clear()
            userList.addAll(it)
        }

        return MainMenuAdapter(userList)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }

    companion object {
            val ARG_LOGIN =""
    }
}