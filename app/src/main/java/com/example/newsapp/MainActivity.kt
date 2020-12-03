package com.example.newsapp


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.viewModel.NewsViewModel


class MainActivity : AppCompatActivity(), ClickInterface {
    private val viewModel by lazy{
        NewsViewModel(application, "politics")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rev = findViewById<RecyclerView>(R.id.rev)
        updateViewModel(rev)


    }
    private fun updateViewModel(rev: RecyclerView){
        viewModel.properties.observe(this, Observer {
            Log.e("list", "$it")
            rev.adapter = NewsApapter(it, this)
            rev.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        })
    }

    override fun newsItem(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.addNews(query!!)
                val rev=findViewById<RecyclerView>(R.id.rev)
                updateViewModel(rev)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@MainActivity, "$newText", Toast.LENGTH_SHORT).show()
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}