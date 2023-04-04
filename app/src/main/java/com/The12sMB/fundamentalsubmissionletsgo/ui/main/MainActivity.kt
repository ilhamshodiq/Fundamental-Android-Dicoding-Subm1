package com.The12sMB.fundamentalsubmissionletsgo.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.The12sMB.fundamentalsubmissionletsgo.GithubUserResponse
import com.The12sMB.fundamentalsubmissionletsgo.R
import com.The12sMB.fundamentalsubmissionletsgo.databinding.ActivityMainBinding
import com.The12sMB.fundamentalsubmissionletsgo.ui.favorite.FavoriteActivity
import com.The12sMB.fundamentalsubmissionletsgo.ui.settings.SettingPreferences
import com.The12sMB.fundamentalsubmissionletsgo.ui.settings.SettingsActivity
import com.The12sMB.fundamentalsubmissionletsgo.ui.settings.SettingsViewModel
import com.The12sMB.fundamentalsubmissionletsgo.ui.settings.SettingsViewModelFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvContent.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvContent.addItemDecoration(itemDecoration)

        mainViewModel.githubuserdata.observe(this) { items ->
            binding.rvContent.adapter = showRecyclerView(items)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        val pref = SettingPreferences.getInstance(dataStore)
        val settingsViewModel =
            ViewModelProvider(this, SettingsViewModelFactory(pref))[SettingsViewModel::class.java]
        settingsViewModel.getThemeSettings().observe(this)
        { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        supportActionBar?.title = "Github User App"
    }

    //search fitur
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()

                mainViewModel.searchGithubUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showRecyclerView(list: List<GithubUserResponse>?): MainMenuAdapter {
        val userList = ArrayList<GithubUserResponse>()

        list?.let {
            userList.clear()
            userList.addAll(it)
        }

        return MainMenuAdapter(userList)
    }


}