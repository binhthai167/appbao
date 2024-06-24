package com.example.both;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.PostAdapter;
import model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiaoDucActivity extends AppCompatActivity {
    private RecyclerView rcvgiaoduc;
    List<Post> mListPost;
    PostAdapter postAdapter;
    private TextView tvusername, tvemail;
    private PostAdapter.RecyclerViewClickListener listener;
    private ImageButton btnsencmt;
    String a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_duc);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rcvgiaoduc = findViewById(R.id.rcv_giaoduc);
        mListPost = new ArrayList<>();
        getListPost();

    }


    private void getListPost() {
        Api.api.getPostgiaoduc().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.code()!=200){
                    Toast.makeText(GiaoDucActivity.this, "Error::"+response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Post> postrec = response.body();
                for (Post post : postrec){
                    mListPost.add(post);
                }
                setDataforAdapter(mListPost);
                Log.e("List Post", ""+ mListPost.size());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(GiaoDucActivity.this, "Call api error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }


    private void setDataforAdapter(List<Post> mListPost) {
        SetOnClickListener();
        postAdapter = new PostAdapter(mListPost, this, listener, a);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvgiaoduc.setLayoutManager(new LinearLayoutManager(this));
        rcvgiaoduc.setAdapter(postAdapter);


    }

    private void SetOnClickListener() {
        listener = new PostAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
            }
        };
    }

}