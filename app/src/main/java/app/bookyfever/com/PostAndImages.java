package app.bookyfever.com;

import java.util.List;

public class PostAndImages {
    public List<Post> mPosts;
    public List<Photo> mPhotos;

    public PostAndImages(List<Post> post, List<Photo> photos) {
        mPosts = post;
        mPhotos = photos;
    }
}
