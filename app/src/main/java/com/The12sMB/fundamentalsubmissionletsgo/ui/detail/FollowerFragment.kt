package com.The12sMB.fundamentalsubmissionletsgo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.The12sMB.fundamentalsubmissionletsgo.GithubUserResponse
import com.The12sMB.fundamentalsubmissionletsgo.R
import com.The12sMB.fundamentalsubmissionletsgo.databinding.FragmentFollowBinding
import com.The12sMB.fundamentalsubmissionletsgo.ui.main.MainMenuAdapter
import com.The12sMB.fundamentalsubmissionletsgo.ui.main.MainViewModel

class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowBinding.bind(view)
        val followerViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)

        val login = arguments?.getString(ARG_LOGIN)
        followerViewModel.getDetailFollower(login.toString())
        followerViewModel.githubuserfollower.observe(viewLifecycleOwner) { items ->
            binding.rvFollow.adapter = showRecyclerView(items)
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

    private fun showLoading(state: Boolean) {
        binding.progressBar2.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
            const val ARG_LOGIN =""
    }
}