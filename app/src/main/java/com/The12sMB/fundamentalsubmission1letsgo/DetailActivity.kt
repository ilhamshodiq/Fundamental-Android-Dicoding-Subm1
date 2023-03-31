package com.The12sMB.fundamentalsubmission1letsgo

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.The12sMB.fundamentalsubmission1letsgo.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val login = intent.getStringExtra(EXTRA_LOGIN)

        if (login != null) {
            mainViewModel.getDetailUser(login)
        }

        mainViewModel.githubuserdetail.observe(this) { item ->
            setDataUser(item)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        // tab layout
        val sectionsPagerAdapter = SectionsPagerAdapter(this, login)//arg ke-2 kirim data login
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        supportActionBar?.title = StringBuilder("Detail: ").append(login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setDataUser(item: GithubUserDetail) {
        binding.apply {
            tvLogin.text = item.login
            tvName.text = item.name
            tvCompany.text = item.company
            tvLocation.text = item.location
            tvFollower.text = item.followers.toString()
            tvFollowing.text = item.following.toString()
            Glide.with(this@DetailActivity)
                .load(item.avatarUrl)
                .into(civProfile)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_LOGIN = "extra_login"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}