package com.example.musicWiki.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicWiki.R
import com.example.musicWiki.model.Tag
import com.example.musicWiki.view.activity.MainActivity

internal class TopTagAdapter(
    list: ArrayList<Tag>, private var mainActivity: MainActivity
) : RecyclerView.Adapter<TopTagAdapter.MyViewHolder>() {


    private var showList = ArrayList<Tag>()

    init {
        showList.clear()
        showList.addAll(list)
    }

    fun refreshList(list: ArrayList<Tag>?) {
        showList.clear()
        showList.addAll(list!!)
        kotlin.run {
            notifyDataSetChanged()
        }
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_tag_name: TextView = view.findViewById(R.id.tv_tag_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.toptagitem, parent, false)

        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return showList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = showList[position]
        holder.tv_tag_name.text = item.name


        holder.tv_tag_name.setOnClickListener {

            mainActivity.callInfoActivity(item.name)

        }
    }

}