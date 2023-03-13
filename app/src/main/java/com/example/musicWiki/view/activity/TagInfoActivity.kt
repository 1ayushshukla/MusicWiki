package com.example.musicWiki.view.activity

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicWiki.R
import com.example.musicWiki.model.Album
import com.example.musicWiki.model.ArtistX
import com.example.musicWiki.model.TagX
import com.example.musicWiki.model.Track
import com.example.musicWiki.view.adapter.AlbumAdapter
import com.example.musicWiki.view.adapter.ArtistAdapter
import com.example.musicWiki.view.adapter.TrackAdapter
import com.example.musicWiki.viewmodel.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_tag_info.*
import java.util.*

class TagInfoActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mainActivityViewModel: MainActivityViewModel
    private var tagName: String? = null
    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var viewManager: GridLayoutManager
    private var artistList = ArrayList<ArtistX>()
    private var albumList = ArrayList<Album>()
    private var trackList = ArrayList<Track>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_info)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        tagName = intent.getStringExtra("tagname")
        CallApi()
        setupUI()
    }

    private fun setupUI() {
        rl_album.setOnClickListener(this)
        rl_artist.setOnClickListener(this)
        rl_track.setOnClickListener(this)
        viewManager = GridLayoutManager(this, 2)
        albumAdapter = AlbumAdapter(ArrayList(), this)
        artistAdapter = ArtistAdapter(ArrayList(), this)
        trackAdapter = TrackAdapter(ArrayList(), this)
        rv_data.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            layoutManager = viewManager
            adapter = albumAdapter
        }
    }

    private fun CallApi() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getTagInfo(tagName)!!.observe(this, Observer { tagInfoData ->
            val list: TagX? = Gson().fromJson(
                Gson().toJson(tagInfoData.tag),
                object : TypeToken<TagX>() {}.type
            )
            if (list != null) {
                autoFillData(list)
            }

        })

        mainActivityViewModel.getTopAlbum(tagName)!!.observe(this, Observer { topAlbum ->
            val list: ArrayList<Album>? = Gson().fromJson(
                Gson().toJson(topAlbum.albums.album),
                object : TypeToken<ArrayList<Album>>() {}.type
            )
            if (list != null) {
                albumList!!.addAll(list)
                albumAdapter.refreshList(list)
            }

        })

        mainActivityViewModel.getTopArtist(tagName)!!.observe(this, Observer { topArtist ->
            val list: ArrayList<ArtistX>? = Gson().fromJson(
                Gson().toJson(topArtist.topartists.artist),
                object : TypeToken<ArrayList<ArtistX>>() {}.type
            )
            if (list != null) {
                artistList!!.addAll(list)
            }

        })


        mainActivityViewModel.getTopTracks(tagName)!!.observe(this, Observer { topTracks ->
            val list: ArrayList<Track>? = Gson().fromJson(
                Gson().toJson(topTracks.tracks.track),
                object : TypeToken<ArrayList<Track>>() {}.type
            )
            if (list != null) {
                trackList!!.addAll(list)
            }

        })

    }

    private fun autoFillData(list: TagX) {
        if (!TextUtils.isEmpty(list.name)) {
            tv_tag_name.text =
                list.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }

        if (!TextUtils.isEmpty(list.wiki.summary)) {
            tv_tag_des.text = list.wiki.summary.toString()
                .substring(0, list.wiki.summary.indexOf("<a"))
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_album -> {
                setTab(vv_one)
                rv_data.apply {
                    setHasFixedSize(false)
                    isNestedScrollingEnabled = false
                    layoutManager = viewManager
                    adapter = albumAdapter
                }

                albumAdapter.refreshList(albumList)
            }
            R.id.rl_artist -> {
                setTab(vv_two)
                rv_data.apply {
                    setHasFixedSize(false)
                    isNestedScrollingEnabled = false
                    layoutManager = viewManager
                    adapter = artistAdapter
                }

                artistAdapter.refreshList(artistList)
            }
            R.id.rl_track -> {
                setTab(vv_three)
                rv_data.apply {
                    setHasFixedSize(false)
                    isNestedScrollingEnabled = false
                    layoutManager = viewManager
                    adapter = trackAdapter
                }

                trackAdapter.refreshList(trackList)
            }
        }
    }

    private fun setTab(view: View) {
        vv_one.setBackgroundColor(Color.parseColor("#8D8D8D"))
        vv_two.setBackgroundColor(Color.parseColor("#8D8D8D"))
        vv_three.setBackgroundColor(Color.parseColor("#8D8D8D"))
        view.setBackgroundColor(resources.getColor(R.color.colorPrimary))
    }
}
