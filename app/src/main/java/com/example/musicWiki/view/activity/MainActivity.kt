package com.example.musicWiki.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicWiki.R
import com.example.musicWiki.model.Tag
import com.example.musicWiki.view.adapter.TopTagAdapter
import com.example.musicWiki.viewmodel.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var topTagAdapter: TopTagAdapter
    private lateinit var listTemp: ArrayList<Tag>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUI()
    }


    private fun setUpUI() {
//        iv_arrow.setOnClickListener(this)
        rl_arrow.setOnClickListener(this)
        val viewManager = GridLayoutManager(this, 3)
        topTagAdapter = TopTagAdapter(ArrayList(), this)
        rv_top_tags.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            layoutManager = viewManager
            adapter = topTagAdapter
        }


        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
            val list: ArrayList<Tag>? = Gson().fromJson(
                Gson().toJson(serviceSetterGetter.toptags.tag),
                object : TypeToken<ArrayList<Tag>>() {}.type
            )
            progress_bar.visibility = View.GONE
            if (list != null) {
                listTemp = list
                topTagAdapter.refreshList(expandView(listTemp, false))
            }

        })

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_arrow -> {
                if (iv_arrow.rotation == 180f) {
                    topTagAdapter.refreshList(expandView(listTemp, true))
                    iv_arrow.rotation = 0f
                } else if (iv_arrow.rotation == 0f) {
                    topTagAdapter.refreshList(expandView(listTemp, false))
                    iv_arrow.rotation = 180f
                }
            }
        }
    }

    private fun expandView(listTemp: ArrayList<Tag>, toExpand: Boolean): ArrayList<Tag> {
        return if (listTemp != null && listTemp.isNotEmpty()) {
            if (toExpand) {
                listTemp
            } else {
                val filterList = ArrayList<Tag>()
                for (i in 0..8) {
                    filterList.add(listTemp[i])
                }
                filterList
            }
        } else {
            listTemp
        }
    }

    fun callInfoActivity(tagName: String?) {
        val intent = Intent(this, TagInfoActivity::class.java)
        intent.putExtra("tagname", tagName)
        startActivity(intent)
    }

}
