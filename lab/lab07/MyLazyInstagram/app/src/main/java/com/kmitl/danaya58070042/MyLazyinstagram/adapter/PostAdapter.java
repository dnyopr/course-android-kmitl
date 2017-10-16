package com.kmitl.danaya58070042.MyLazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kmitl.danaya58070042.lazyinstagram.R;
import com.kmitl.danaya58070042.MyLazyinstagram.api.Post;

import java.util.List;

/**
 * Created by student on 10/6/2017 AD.
 */


class Holder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView like, comment;



    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        like = (TextView) itemView.findViewById(R.id.like);
        comment = (TextView) itemView.findViewById(R.id.comment);
    }
}

public class PostAdapter
        extends RecyclerView.Adapter<Holder> {


    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        showPost(holder, position);
    }

    public void showPost(Holder holder, int position){
        ImageView imageView = holder.image;
        Glide.with(context).load(posts.get(position).getUrl()).into(imageView);

        TextView like = holder.like;
        like.setText(String.valueOf(posts.get(position).getLike()));

        TextView comment = holder.comment;
        comment.setText(String.valueOf(posts.get(position).getComment()));
    }


}
