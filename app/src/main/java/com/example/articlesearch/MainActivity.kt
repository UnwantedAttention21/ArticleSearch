package com.example.articlesearch

import Article
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.example.flixster.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val apiKey = "F3QYDgvC5ggflwBTO8ALM9I312mlNe78"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ArticleApiService::class.java)

        service.getTopHeadlines("us", apiKey).enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: retrofit2.Response<ArticleResponse>
            ) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    recyclerView.adapter = ArticleAdapter(articles) { article: Article ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("article", article)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                // Handle failure
                t.printStackTrace()
            }
        })
    }
}
