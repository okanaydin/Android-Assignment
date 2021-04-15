package app.storytel.candidate.com;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ScrollingActivity extends AppCompatActivity {
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos";
    RecyclerView mRecyclerView;
    PostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mPostAdapter = new PostAdapter(Glide.with(this), this);
        mRecyclerView.setAdapter(mPostAdapter);

        new AsyncTask<Void, Void, PostAndImages>() {
            @Override
            protected PostAndImages doInBackground(Void... voids) {
                List<Post> posts = getPosts();
                List<Photo> photos = getPhotos();
                return new PostAndImages(posts, photos);
            }

            private List<Post> getPosts()
            {
                List<Post> posts = null;
                InputStream stream = null;
                HttpURLConnection urlConnection = null;
                try {
                    String result = null;
                    URL url = new URL(POSTS_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        throw new IOException("HTTP error code: " + responseCode);
                    }
                    stream = urlConnection.getInputStream();
                    if (stream != null) {
                        result = readStream(stream);
                        Post[] array = new Gson().fromJson(result, Post[].class);
                        posts = Arrays.asList(array);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

                return posts;
            }

            private List<Photo> getPhotos()
            {
                List<Photo> photos = null;
                InputStream stream = null;
                HttpURLConnection urlConnection = null;
                try {
                    String result = null;
                    URL url = new URL(PHOTOS_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        throw new IOException("HTTP error code: " + responseCode);
                    }
                    stream = urlConnection.getInputStream();
                    if (stream != null) {
                        result = readStream(stream);
                        Photo[] array = new Gson().fromJson(result, Photo[].class);
                        photos = Arrays.asList(array);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

                return photos;
            }

            /**
             * Converts the contents of an InputStream to a String.
             */
            public String readStream(InputStream stream)
                    throws IOException, UnsupportedEncodingException {
                Reader reader = null;
                reader = new InputStreamReader(stream, "UTF-8");
                char[] rawBuffer = new char[256];
                int readSize;
                StringBuffer buffer = new StringBuffer();
                while (((readSize = reader.read(rawBuffer)) != -1)) {
                    buffer.append(rawBuffer, 0, readSize);
                }
                return buffer.toString();
            }

            @Override
            protected void onPostExecute(PostAndImages result) {
                mPostAdapter.setData(result);
            }

        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
