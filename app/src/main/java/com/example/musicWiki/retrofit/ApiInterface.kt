package com.example.musicWiki.retrofit

import com.example.musicWiki.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/2.0")
    fun getTopTags(
        @Query("method") method: String = "tag.getTopTags",
        @Query("api_key") apiKey: String = "5f42bbc0d587fd2383ef51c573c5da70",
        @Query("format") format: String = "json"
    ): Call<TT>


    @GET("/2.0")
    fun getTagInfo(
        @Query("tag") tag: String,
        @Query("method") method: String = "tag.getinfo",
        @Query("api_key") apiKey: String = "5f42bbc0d587fd2383ef51c573c5da70",
        @Query("format") format: String = "json"
    ): Call<TI>

    @GET("/2.0")
    fun getTopAlbums(
        @Query("tag") tag: String,
        @Query("method") method: String = "tag.getTopAlbums",
        @Query("api_key") apiKey: String = "5f42bbc0d587fd2383ef51c573c5da70",
        @Query("format") format: String = "json"
    ): Call<TA>


    @GET("/2.0")
    fun getTagArtists(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopArtists",
        @Query("api_key") apiKey:String="5f42bbc0d587fd2383ef51c573c5da70",
        @Query("format") format:String="json"
    ): Call<TAR>

    @GET("/2.0")
    fun getTagTracks(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopTracks",
        @Query("api_key") apiKey:String="5f42bbc0d587fd2383ef51c573c5da70",
        @Query("format") format:String="json"
    ): Call<TTR>
}