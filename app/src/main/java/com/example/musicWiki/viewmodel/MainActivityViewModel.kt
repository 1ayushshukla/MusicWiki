package com.example.musicWiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicWiki.model.*
import com.example.musicWiki.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<TT>? = null
    var tagInfoData: MutableLiveData<TI>? = null
    var topAlbum: MutableLiveData<TA>? = null
    var topArtist: MutableLiveData<TAR>? = null
    var topTracks: MutableLiveData<TTR>? = null


    fun getUser(): MutableLiveData<TT>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall()
        return servicesLiveData
    }


    fun getTagInfo(tagName: String?): MutableLiveData<TI>? {
        tagInfoData = MainActivityRepository.getTagInfoApiCall(tagName)
        return tagInfoData
    }

    fun getTopAlbum(tagName: String?): MutableLiveData<TA>? {
        topAlbum = MainActivityRepository.getTopAlbumApiCall(tagName)
        return topAlbum
    }


    fun getTopArtist(tagName: String?): MutableLiveData<TAR>? {
        topArtist = MainActivityRepository.getTopArtistApiCall(tagName)
        return topArtist
    }

    fun getTopTracks(tagName: String?): MutableLiveData<TTR>? {
        topTracks = MainActivityRepository.getTopTracksApiCall(tagName)
        return topTracks
    }


}