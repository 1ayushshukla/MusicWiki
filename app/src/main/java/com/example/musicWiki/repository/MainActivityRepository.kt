package com.example.musicWiki.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.musicWiki.model.*
import com.example.musicWiki.retrofit.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<TT>()
    val tagInfoData = MutableLiveData<TI>()
    val topAlbum = MutableLiveData<TA>()
    val topArtist = MutableLiveData<TAR>()
    val topTracks = MutableLiveData<TTR>()

    fun getServicesApiCall(): MutableLiveData<TT> {

        val call = RetrofitClient.apiInterface.getTopTags()

        call.enqueue(object : Callback<TT> {
            override fun onFailure(call: Call<TT>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<TT>,
                response: Response<TT>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

//                val msg = data!!.message

                serviceSetterGetter.value = data
            }
        })

        return serviceSetterGetter
    }

    fun getTagInfoApiCall(tagName: String?): MutableLiveData<TI> {

        val call = RetrofitClient.apiInterface.getTagInfo(tagName!!)

        call.enqueue(object : Callback<TI> {
            override fun onFailure(call: Call<TI>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<TI>,
                response: Response<TI>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

//                val msg = data!!.message

                tagInfoData.value = data
            }
        })

        return tagInfoData
    }


    fun getTopAlbumApiCall(tagName: String?): MutableLiveData<TA> {

        val call = RetrofitClient.apiInterface.getTopAlbums(tagName!!)

        call.enqueue(object : Callback<TA> {
            override fun onFailure(call: Call<TA>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<TA>,
                response: Response<TA>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

//                val msg = data!!.message
                topAlbum.value = data
            }
        })

        return topAlbum
    }

    fun getTopArtistApiCall(tagName: String?): MutableLiveData<TAR> {

        val call = RetrofitClient.apiInterface.getTagArtists(tagName!!)

        call.enqueue(object : Callback<TAR> {
            override fun onFailure(call: Call<TAR>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<TAR>,
                response: Response<TAR>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                Log.i("PPP", Gson().toJson(data))
                topArtist.value = data
            }
        })

        return topArtist
    }


    fun getTopTracksApiCall(tagName: String?): MutableLiveData<TTR> {

        val call = RetrofitClient.apiInterface.getTagTracks(tagName!!)

        call.enqueue(object : Callback<TTR> {
            override fun onFailure(call: Call<TTR>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<TTR>,
                response: Response<TTR>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                Log.i("PPP", Gson().toJson(data))
                topTracks.value = data
            }
        })

        return topTracks
    }
}
