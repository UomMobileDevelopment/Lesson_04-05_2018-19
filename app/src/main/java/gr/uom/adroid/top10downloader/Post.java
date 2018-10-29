package gr.uom.adroid.top10downloader;

/**
 * Created by user on 29/10/2018.
 */

class Post {

    private String userId;
    private String postId;
    private String postTitle;
    private String postBody;

    public Post(String userId, String postId, String postTitle, String postBody) {
        this.userId = userId;
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postBody='" + postBody + '\'' +
                '}';
    }
}



