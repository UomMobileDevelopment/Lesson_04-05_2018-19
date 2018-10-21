package gr.uom.adroid.top10downloader;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by teohaik on 21/10/2018.
 */

public class JSONParser {

    private static final String TAG = "JSONParser";

    public static final String USER_ID_KEY = "userId";
    public static final String POST_ID_KEY = "id";
    public static final String TITLE_KEY = "title";
    public static final String BODY_KEY = "body";


    private ArrayList<Post> posts;

    public JSONParser(){
        posts = new ArrayList<>();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public boolean parse(String jsonData){
        try {
            JSONArray jsonPostArray = new JSONArray(jsonData);
            for(int i=0; i<jsonPostArray.length(); i++){
                JSONObject jsonPost = jsonPostArray.getJSONObject(i);
                int userId = jsonPost.getInt(USER_ID_KEY);
                int postId = jsonPost.getInt(POST_ID_KEY);
                String postTitle = jsonPost.getString(TITLE_KEY);
                String postBody = jsonPost.getString(BODY_KEY);

                Post p = new Post();
                p.setPostId(postId);
                p.setUserId(userId);
                p.setTitle(postTitle);
                p.setBody(postBody);

                posts.add(p);
            }
        } catch (JSONException e) {
            Log.e(TAG, "parse: Error parsing json data", e);
            return false;
        }
        return true;
    }
}
