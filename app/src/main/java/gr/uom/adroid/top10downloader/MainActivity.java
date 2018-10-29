package gr.uom.adroid.top10downloader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TeoMainActivity";

    private ListView postListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postListView = findViewById(R.id.postListView);

        Log.d(TAG, "onCreate: starting an async Task....");

        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://jsonplaceholder.typicode.com/posts");

        Log.d(TAG, "onCreate: DONE");
    }

    private class DownloadData extends AsyncTask<String, Void, String>{
        private static final String TAG = "TeoDownloadDataTask";

        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);
            Log.d(TAG, "onPostExecute: "+jsonData);

            JSONParser parser = new JSONParser();
            List<Post> posts = parser.parseJson(jsonData);

//           ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(
//                   MainActivity.this, R.layout.list_item, posts  );
//           postListView.setAdapter(adapter);

            PostAdapter postAdapter = new
                    PostAdapter(MainActivity.this,
                        R.layout.list_record,
                        posts);

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
                int reponseCode = connection.getResponseCode();
                Log.d(TAG, "downloadJSON: Response code was " + reponseCode);

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
