package com.example.articlesearch

import Article
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.flixster.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val article = intent.getSerializableExtra("article") as? Article

        val imageView = findViewById<ImageView>(R.id.detailImageView)
        val titleTextView = findViewById<TextView>(R.id.detailTitleTextView)
        val authorTextView = findViewById<TextView>(R.id.detailAuthorTextView)
        val descriptionTextView = findViewById<TextView>(R.id.detailDescriptionTextView)

        if (article != null) {
            titleTextView.text = article.title
        }
        if (article != null) {
            authorTextView.text = "By ${article.author}"
        }
        if (article != null) {
            descriptionTextView.text = article.description
        }
        if (article != null) {
            Glide.with(this).load(article.mediaUrl).into(imageView)
        }
    }
}
