package gr.uom.adroid.top10downloader;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private static final String TAG = "JSONParser";

    public static final String USER_ID_KEY = "userId";
    public static final String POST_ID_KEY = "id";
    public static final String POST_TITLE_KEY = "title";
    public static final String POST_BODY_KEY = "body";

    private List<Post> posts;

    public JSONParser(){
        posts = new ArrayList<>();
    }

    public List<Post> parseJson(String jsonText){
        try{
            JSONArray jsonPostArray = new JSONArray(jsonText);

            for(int i=0; i<jsonPostArray.length(); i++){
                JSONObject jsonPost = jsonPostArray.getJSONObject(i);
                String userId = jsonPost.getString(USER_ID_KEY);
                String postId = jsonPost.getString(POST_ID_KEY);
                String title = jsonPost.getString(POST_TITLE_KEY);
                String body = jsonPost.getString(POST_BODY_KEY);

                Post p = new Post(userId, postId, title, body);

                posts.add(p);
            }


        } catch (JSONException e) {
            Log.e(TAG, "parseJson: PARSING ERROR " , e );
        }


        return posts;
    }

}
