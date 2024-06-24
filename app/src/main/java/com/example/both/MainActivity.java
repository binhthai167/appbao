package com.example.both;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import Adapter.CmtAdapter;
import Adapter.PostAdapter;
import model.Cmt;
import model.Post;
import model.ResponeCmt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvParent, rcvCon;
    List<Post> mListPost;
    PostAdapter postAdapter;
    private TextView tvusername, tvemail;
    private PostAdapter.RecyclerViewClickListener listener;
    TextView act_main_tv_username, act_main_tv_email, tvtitle, tvdes, tvpub, tvuser, tvthoisu,
            tvthethao, tvkhoahoc, tvgiaoduc, tvgiaitri;
    EditText edtaddcmt;
    private ImageButton btnsencmt;
    String a;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.act_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        tvtitle = findViewById(R.id.tv_TitlePost);
        tvtitle = findViewById(R.id.tv_DesPost);
        tvpub = findViewById(R.id.tv_pubday);
        tvuser=findViewById(R.id.tv_user);

        tvthoisu = findViewById(R.id.tv_thoisu);
        tvthoisu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, thoisuActivity.class);
                startActivity(i);
            }
        });

        tvthethao = findViewById(R.id.tv_thethao);
        tvthethao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, thethaoActivity.class);
                startActivity(i);
            }
        });

        tvkhoahoc = findViewById(R.id.tv_khoahoc);
        tvkhoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, KhoaHocActivity.class);
                startActivity(i);
            }
        });

        tvgiaoduc = findViewById(R.id.tv_giaoduc);
        tvgiaoduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GiaoDucActivity.class);
                startActivity(i);
            }
        });

        tvgiaitri = findViewById(R.id.tv_giaitri);
        tvgiaitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GiaiTriActivity.class);
                startActivity(i);
            }
        });




        act_main_tv_email = findViewById(R.id.act_main_tv_email);
        act_main_tv_username = findViewById(R.id.act_main_tv_username);
        a = getIntent().getStringExtra("user");
        String y = getIntent().getStringExtra("user");
        act_main_tv_username.setText(a);
        
        String b = getIntent().getStringExtra("email");
        act_main_tv_email.setText(b);



        rcvParent = findViewById(R.id.rcv_parent);
        edtaddcmt = findViewById(R.id.edt_addcoment);
        btnsencmt = findViewById(R.id.btn_comment);


        mListPost = new ArrayList<>();
        getListPost();

    }




    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }





    private void getListPost() {
        Api.api.getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.code()!=200){
                    Toast.makeText(MainActivity.this, "Error::"+response.errorBody(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "Call api error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }


    private void setDataforAdapter(List<Post> mListPost) {
            SetOnClickListener();
            postAdapter = new PostAdapter(mListPost, this, listener, a);
            RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rcvParent.setLayoutManager(new LinearLayoutManager(this));
            rcvParent.setAdapter(postAdapter);


    }

    private void SetOnClickListener() {
            listener = new PostAdapter.RecyclerViewClickListener() {
                @Override
                public void onClick(View v, int position) {
                }
            };
    }


}