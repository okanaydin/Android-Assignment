package app.storytel.candidate.com;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private static final String COMMENTS_URL = "https://jsonplaceholder.typicode.com/posts/{id}/comments";
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mImageView = findViewById(R.id.backdrop);
        mTextView = findViewById(R.id.details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO display the selected post from ScrollingActivity. Use mImageView and mTextView for image and body text. Change the title to use the post title
        //TODO load top 3 comments from COMMENTS_URL into the 3 card views
    }

}
