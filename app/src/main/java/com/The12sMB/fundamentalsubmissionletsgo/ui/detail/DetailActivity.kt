package com.The12sMB.fundamentalsubmissionletsgo.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.The12sMB.fundamentalsubmissionletsgo.GithubUserDetail
import com.The12sMB.fundamentalsubmissionletsgo.R
import com.The12sMB.fundamentalsubmissionletsgo.data.local.entity.FavoriteUser
import com.The12sMB.fundamentalsubmissionletsgo.databinding.ActivityDetailBinding
import com.The12sMB.fundamentalsubmissionletsgo.ui.ViewModelFactory
import com.The12sMB.fundamentalsubmissionletsgo.ui.main.MainViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var favoriteUser: FavoriteUser

    private val detailViewModel by viewModels<DetailFavViewModel> {
        ViewModelFactory.getInstance(
            application
        )
    }

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


        //favorite


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
        favoriteUser = FavoriteUser(
            item.id.toString(), item.login, item.avatarUrl,
            item.htmlUrl
        )
        detailViewModel.isFav(favoriteUser.userId).observe(this) {
            setFavUser(it)
        }
    }

    private fun setFavUser(value: Boolean) {
        binding.floatingActionButton3.setOnClickListener {
            if (value) {
                detailViewModel.delete(favoriteUser)
                binding.floatingActionButton3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite_border
                    )
                )
            } else {
                detailViewModel.insert(favoriteUser)
                binding.floatingActionButton3.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )
            }
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