package gr.uom.adroid.top10downloader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TeoMainActivity";

    private ListView postListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postListView = findViewById(R.id.postListView);

        downloadDataFromUrl("https://jsonplaceholder.typicode.com/posts");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menuUser1Posts:
                Toast.makeText(this, "Showing posts of user 1", Toast.LENGTH_LONG).show();
                downloadDataFromUrl("https://jsonplaceholder.typicode.com/posts?userId=1");
                break;
            case R.id.menuUser2Posts:

                Toast.makeText(this, "Showing posts of user 2", Toast.LENGTH_LONG).show();
                downloadDataFromUrl("https://jsonplaceholder.typicode.com/posts?userId=2");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void downloadDataFromUrl(String feedUrl){

        DownloadData downloadData = new DownloadData();
        downloadData.execute(feedUrl);

    }

    private class DownloadData extends AsyncTask<String, Void, String>{
        private static final String TAG = "TeoDownloadDataTask";

        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);
            Log.d(TAG, "onPostExecute parameter is " + jsonData );
            JSONParser parser = new JSONParser();
            parser.parse(jsonData);

            ArrayList<Post> posts = parser.getPosts();

            for(int i =0; i<posts.size(); i++){
                Log.d(TAG, posts.get(i).toString());
                Log.d(TAG, "-------------------------------");
            }

//            ArrayAdapter<Post> postAdapter = new ArrayAdapter<Post>(
//                    MainActivity.this, R.layout.list_item, posts);
//
//            postListView.setAdapter(postAdapter);

            PostAdapter postAdapter = new PostAdapter(MainActivity.this, R.layout.list_record, posts);

            postListView.setAdapter(postAdapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground starts with: " + strings[0]);
            String postData = downloadJSON(strings[0]);
            if(postData == null){
                Log.e(TAG, "doInBackground: Error downloading from url " + strings[0] );
            }
            return postData;
        }

        private String downloadJSON(String urlPath) {
            StringBuilder sb = new StringBuilder();

            try{
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "downloadJSON: Response code was " + responseCode);

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line = reader.readLine();
                while(line != null){
                    sb.append(line).append("\n");
                    line = reader.readLine();
                }

                reader.close();

            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadJSON: not correct URL: "+urlPath , e);
            } catch (IOException e) {
                Log.e(TAG, "downloadJSON: io error ",e);
            }

            return sb.toString();
        }
    }
}
