package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import app.storytel.candidate.com.R

class DetailsActivity : AppCompatActivity() {
    private var mImageView: ImageView? = null
    private var mTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        mImageView = findViewById(R.id.backdrop)
        mTextView = findViewById(R.id.details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // TODO display the selected post from ScrollingActivity. Use mImageView and mTextView for image and body text. Change the title to use the post title
        // TODO load top 3 comments from COMMENTS_URL into the 3 card views
    }

    companion object {
        private const val COMMENTS_URL = "https://jsonplaceholder.typicode.com/posts/{id}/comments"
    }
}
