package com.The12sMB.fundamentalsubmission1letsgo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.The12sMB.fundamentalsubmission1letsgo.apis.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _githubuserdata = MutableLiveData<List<GithubUserResponse>>()
    val githubuserdata: LiveData<List<GithubUserResponse>> = _githubuserdata

    private val _githubuserdetail = MutableLiveData<GithubUserDetail>()
    val githubuserdetail: LiveData<GithubUserDetail> = _githubuserdetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        //random huruf
        fun randomChar(): String {
            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            return chars.random().toString()
        }
        searchGithubUser(randomChar())
    }

    private fun searchGithubUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(query)
        client.enqueue(object : Callback<GithubUserSearchResponse> {
            override fun onResponse(
                call: Call<GithubUserSearchResponse>,
                response: Response<GithubUserSearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _githubuserdata.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserSearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(query)
        client.enqueue(object : Callback<GithubUserDetail> {
            override fun onResponse(
                call: Call<GithubUserDetail>,
                response: Response<GithubUserDetail>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _githubuserdetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}