package com.example.demosocialuserapitest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosocialuserapitest.PostUtils.UserPost;
import com.example.demosocialuserapitest.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private List<UserPost> userPostList;

    public PostAdapter(Context context, List<UserPost> userPostList) {
        this.context = context;
        this.userPostList = userPostList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_info, parent, false);
        return new PostAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        final UserPost  userPost = userPostList.get(position);

        holder.titleTV.setText(userPost.getTitle());
        holder.bodyTV.setText(userPost.getBody());
    }

    @Override
    public int getItemCount() {
        return userPostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, bodyTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.tvTitle);
            bodyTV = itemView.findViewById(R.id.tvBody);
        }
    }
}
