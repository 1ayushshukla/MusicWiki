package com.example.musicWiki.view.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicWiki.R
import com.example.musicWiki.model.Track
import com.example.musicWiki.view.activity.TagInfoActivity

internal class TrackAdapter(
    list: ArrayList<Track>, private var tagInfoActivity: TagInfoActivity
) : RecyclerView.Adapter<TrackAdapter.MyViewHolder>() {


    private var showList = ArrayList<Track>()

    init {
        showList.clear()
        showList.addAll(list)
    }

    fun refreshList(list: ArrayList<Track>?) {
        showList.clear()
        showList.addAll(list!!)
        kotlin.run {
            notifyDataSetChanged()
        }
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_album: ImageView = view.findViewById(R.id.iv_album)
        var tv_album_name: TextView = view.findViewById(R.id.tv_album_name)
        var tv_artist_name: TextView = view.findViewById(R.id.tv_artist_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)

        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return showList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = showList[position]
        if (!TextUtils.isEmpty(item.image[2].text)) {
            Glide.with(holder.iv_album).load(item.image[2].text).into(holder.iv_album)
        }
        holder.tv_artist_name.text = item.artist.name
        holder.tv_album_name.text = item.name
//        Log.i("PPP", item.image[2].text)

    }

}