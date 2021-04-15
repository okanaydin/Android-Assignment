package app.storytel.candidate.com

import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.Arrays
import javax.net.ssl.HttpsURLConnection

class ScrollingActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    var mPostAdapter: PostAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        mRecyclerView = findViewById(R.id.recycler_view)
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.setLayoutManager(manager)
        mPostAdapter = PostAdapter(Glide.with(this), this)
        mRecyclerView.setAdapter(mPostAdapter)
        object : AsyncTask<Void?, Void?, PostAndImages>() {
            override fun doInBackground(vararg params: Void?): PostAndImages {
                val posts = posts
                val photos = photos
                return PostAndImages(posts!!, photos!!)
            }

            private val posts: List<Post>?
                private get() {
                    var posts: List<Post>? = null
                    var stream: InputStream? = null
                    var urlConnection: HttpURLConnection? = null
                    try {
                        var result: String? = null
                        val url = URL(POSTS_URL)
                        urlConnection = url.openConnection() as HttpURLConnection
                        urlConnection.connect()
                        val responseCode = urlConnection!!.responseCode
                        if (responseCode != HttpsURLConnection.HTTP_OK) {
                            throw IOException("HTTP error code: $responseCode")
                        }
                        stream = urlConnection.inputStream
                        if (stream != null) {
                            result = readStream(stream)
                            val array = Gson().fromJson(result, Array<Post>::class.java)
                            posts = Arrays.asList(*array)
                        }
                    } catch (e: MalformedURLException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        if (stream != null) {
                            try {
                                stream.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                        urlConnection?.disconnect()
                    }
                    return posts
                }
            private val photos: List<Photo>?
                private get() {
                    var photos: List<Photo>? = null
                    var stream: InputStream? = null
                    var urlConnection: HttpURLConnection? = null
                    try {
                        var result: String? = null
                        val url = URL(PHOTOS_URL)
                        urlConnection = url.openConnection() as HttpURLConnection
                        urlConnection!!.connect()
                        val responseCode = urlConnection!!.responseCode
                        if (responseCode != HttpsURLConnection.HTTP_OK) {
                            throw IOException("HTTP error code: $responseCode")
                        }
                        stream = urlConnection!!.inputStream
                        if (stream != null) {
                            result = readStream(stream)
                            val array = Gson().fromJson(result, Array<Photo>::class.java)
                            photos = Arrays.asList(*array)
                        }
                    } catch (e: MalformedURLException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        if (stream != null) {
                            try {
                                stream!!.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                        if (urlConnection != null) {
                            urlConnection!!.disconnect()
                        }
                    }
                    return photos
                }

            /**
             * Converts the contents of an InputStream to a String.
             */
            @Throws(IOException::class, UnsupportedEncodingException::class)
            fun readStream(stream: InputStream?): String {
                var reader: Reader? = null
                reader = InputStreamReader(stream, "UTF-8")
                val rawBuffer = CharArray(256)
                var readSize: Int
                val buffer = StringBuffer()
                while (reader.read(rawBuffer).also { readSize = it } != -1) {
                    buffer.append(rawBuffer, 0, readSize)
                }
                return buffer.toString()
            }

            override fun onPostExecute(result: PostAndImages) {
                mPostAdapter!!.setData(result)
            }
        }.execute()
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

    companion object {
        private const val POSTS_URL = "https://jsonplaceholder.typicode.com/posts"
        private const val PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos"
    }
}