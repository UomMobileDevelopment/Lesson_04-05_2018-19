package gr.uom.adroid.top10downloader;

/**
 * Created by teohaik on 21/10/2018.
 */

public class Post {

    private int userId;
    private int postId;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}


