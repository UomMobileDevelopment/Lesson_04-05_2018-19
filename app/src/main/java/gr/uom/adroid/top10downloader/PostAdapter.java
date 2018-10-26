package gr.uom.adroid.top10downloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by teohaik on 26/10/2018.
 */

public class PostAdapter extends ArrayAdapter<Post> {

    private static final String TAG = "PostAdapter";

    private final LayoutInflater inflater;
    private final int layoutResource;
    private List<Post> posts;

    public PostAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        layoutResource = resource;
        posts = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(layoutResource, parent, false);
        TextView postId = view.findViewById(R.id.txtPostId);
        TextView postTitle = view.findViewById(R.id.txtPostTitle);
        TextView postBody = view.findViewById(R.id.txtPostBody);

        Post post = posts.get(position);

        postId.setText(post.getPostId()+"");
        postTitle.setText(post.getTitle()+"");
        postBody.setText(post.getBody()+"");

        return view;
    }

}
