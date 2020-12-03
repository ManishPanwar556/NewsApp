package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.models.Article
import com.example.newsapp.models.News
import com.example.newsapp.room.NewsEntity

class NewsApapter(val news: List<NewsEntity>,val clickInterface:ClickInterface): RecyclerView.Adapter<NewsApapter.NewsViewHolder>(){
    inner class NewsViewHolder(val view: View):RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                if(adapterPosition!=RecyclerView.NO_POSITION){
                    clickInterface.newsItem(news[adapterPosition].newsUrl)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val title=holder.view.findViewById<TextView>(R.id.titleText)
        val imageView=holder.view.findViewById<ImageView>(R.id.imageView)
        Glide.with(holder.view).load(news[position].imageUrl).into(imageView)
        title.text=news[position].title
    }

    override fun getItemCount()=news.size

}