package app.storytel.candidate.com.features.posts.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.storytel.candidate.com.R
import app.storytel.candidate.com.data.remote.datasource.model.PostAndPhotoModel
import app.storytel.candidate.com.features.details.ui.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScrollingActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView

    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        mRecyclerView = findViewById(R.id.recycler_view)
        subscribeUi()
    }

    private fun subscribeUi() {
        postViewModel.layoutViewState.observe(this) { state ->
            when {
                state.isLoading() -> {
                    // TODO implement loading case
                }
            }
        }
        postViewModel.postList.observe(this) { postAndPhotoList ->
            createPostList(postAndPhotoList)
        }
    }

    private fun createPostList(postList: List<PostAndPhotoModel>) {

        val list = postList.toMutableList()

        if (mRecyclerView.adapter == null) {
            val adapter = PostAdapter(
                list,
                object : PostAdapter.PostItemClick {
                    override fun onClick(postItem: PostAndPhotoModel) {
                        // TODO change with navigation component
                        startActivity(Intent(this@ScrollingActivity, DetailsActivity::class.java))
                    }
                }
            )
            mRecyclerView.adapter = adapter
        } else {
            (mRecyclerView.adapter as PostAdapter).updateList(list)
        }

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = manager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
