package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.both.DetailActivity;
import com.example.both.R;
import com.example.both.thoisuActivity;

import java.util.List;

import model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context mContext;
    private List<Post> mListPost;
    private RecyclerViewClickListener listener;
    String username;


    public PostAdapter(List<Post> mListPost, Context mcontext, RecyclerViewClickListener listener, String username) {
        this.mListPost = mListPost;
        this.mContext = mcontext;
        this.listener = listener;
        this.username = username;
    }




    public void setData(List<Post> list){
        this.mListPost = list;
        notifyDataSetChanged();
    }

    @NonNull

    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_post, parent, false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = mListPost.get(position);
        if(post==null){
            return; }
        holder.tvTitle.setText(post.getTitle());
        holder.tvDes.setText(post.getDescription());
        holder.tvPub.setText(post.getPubday());
        Glide.with(mContext)
                .load(post.getThumbnail())
                .into(holder.imvPost);
    }

    @Override
    public int getItemCount() {
        if(mListPost!=null){
            return mListPost.size();
        }
        return 0;
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imvPost;
        private TextView tvTitle;
        private TextView tvDes;
        private TextView tvPub;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imvPost = itemView.findViewById(R.id.imvPost);
            tvTitle = itemView.findViewById(R.id.tv_TitlePost);
            tvDes = itemView.findViewById(R.id.tv_DesPost);
            tvPub = itemView.findViewById(R.id.tv_pubday);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAbsoluteAdapterPosition();
            Intent i = new Intent(mContext, DetailActivity.class);
            i.putExtra("img", mListPost.get(position).getThumbnail());
            i.putExtra("title", mListPost.get(position).getTitle());
            i.putExtra("des", mListPost.get(position).getDescription());
            i.putExtra("pub", mListPost.get(position).getPubday());
            i.putExtra("ss", username);
            mContext.startActivity(i);

            listener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
