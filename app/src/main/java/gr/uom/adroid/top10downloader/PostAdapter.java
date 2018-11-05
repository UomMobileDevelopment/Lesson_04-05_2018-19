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
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Post post = posts.get(position);

        viewHolder.postId.setText(post.getPostId()+"");
        viewHolder.postTitle.setText(post.getTitle());
        viewHolder.postBody.setText(post.getBody());

        return convertView;
    }

    private class ViewHolder {
        final TextView postId;
        final TextView postTitle;
        final TextView postBody;

        ViewHolder(View view){
            postId = view.findViewById(R.id.txtPostId);
            postTitle = view.findViewById(R.id.txtPostTitle);
            postBody = view.findViewById(R.id.txtPostBody);
        }
    }

}
