package com.The12sMB.fundamentalsubmission1letsgo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity, private val username: String?) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowerFragment()
                username?.let {
                    (fragment as FollowerFragment).arguments = Bundle().apply {
                        putString(FollowerFragment.ARG_LOGIN, it)
                    }
                }
            }
            1 -> {
                fragment = FollowingFragment()
                username?.let {
                    (fragment as FollowingFragment).arguments = Bundle().apply {
                        putString(FollowingFragment.ARG_LOGIN, it)
                    }
                }
            }
        }
        return fragment as Fragment
    }

}