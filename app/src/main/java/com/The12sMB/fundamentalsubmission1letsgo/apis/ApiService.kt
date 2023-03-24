package com.The12sMB.fundamentalsubmission1letsgo.apis

import com.The12sMB.fundamentalsubmission1letsgo.GithubUserDetail
import com.The12sMB.fundamentalsubmission1letsgo.GithubUserResponse
import com.The12sMB.fundamentalsubmission1letsgo.GithubUserSearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getSearchUser(//Search
        @Query("q") query: String
    ): Call<GithubUserSearchResponse>

    @GET("users/{username}")
    fun getDetailUser(//Detail user
        @Path("username") username: String?
    ): Call<GithubUserDetail>

    @GET("users/{username}/followers")
    fun getFollowers(//List Follower
        @Path("username") username: String?
    ): Call<List<GithubUserResponse>>

    @GET("users/{username}/following")
    fun getFollowing(//List Following
        @Path("username") username: String?
    ): Call<List<GithubUserResponse>>
}
