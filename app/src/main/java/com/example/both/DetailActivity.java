package com.example.both;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Adapter.CmtAdapter;
import Adapter.PostAdapter;
import model.Cmt;
import model.ResponeCmt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView  rcvCon;
    List<Cmt> mListCmt;
    CmtAdapter cmtAdapter;
    private TextView tvname1, tvcontent, tvtitle, tvdes, tvpub, user1, tvbl, tvnguoibl;
    EditText edtaddcmt;
    ImageButton btnaddcmt;
    ImageView imgview;
    private PostAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_baseline_arrow_back_24);
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvtitle = findViewById(R.id.tv_TitlePost1);
        tvdes = findViewById(R.id.tv_DesPost1);
        tvpub = findViewById(R.id.tv_pubday1);
        user1 = findViewById(R.id.tv_user1);
        imgview = findViewById(R.id.imvPost1);
        tvbl = findViewById(R.id.tv_binhluan);
        tvnguoibl = findViewById(R.id.tv_nguoi_bl);

        String he = getIntent().getStringExtra("img");

        Glide.with(DetailActivity.this)
                .load(he)
                .into(imgview);
        String a = getIntent().getStringExtra("title");
        tvtitle.setText(a);
        String b = getIntent().getStringExtra("des");
        tvdes.setText(b);
        String c = getIntent().getStringExtra("pub");
        tvpub.setText(c);
//        user1.setText(getIntent().getStringExtra("ss"));
        edtaddcmt = findViewById(R.id.edt_addcoment1);


        tvcontent = findViewById(R.id.tv_cmt);
        rcvCon = findViewById(R.id.rcv_con);
        mListCmt = new ArrayList<>();
        getListCmt();
        btnaddcmt = findViewById(R.id.btn_comment1);
        btnaddcmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvnguoibl.setText(edtaddcmt.getText());
                tvbl.setText(getIntent().getStringExtra("ss"));


                Call<ResponeCmt> c = Api.api.addCmt(String.valueOf(edtaddcmt.getText()), String.valueOf(user1.getText()));

                c.enqueue(new Callback<ResponeCmt>() {
                    @Override
                    public void onResponse(Call<ResponeCmt> call, Response<ResponeCmt> response) {
                        Toast.makeText(DetailActivity.this, response.body().getMess(), Toast.LENGTH_SHORT).show();

                        edtaddcmt.setText("");

                    }

                    @Override
                    public void onFailure(Call<ResponeCmt> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, "bug", Toast.LENGTH_SHORT).show();
                        System.out.println("huhuhuhu");

                    }
                });
            }
        });


    }


    private void getListCmt(){
        Api.api.getCmt().enqueue(new Callback<List<Cmt>>() {
            @Override
            public void onResponse(Call<List<Cmt>> call, Response<List<Cmt>> response) {
                if(response.code()!=200){
                    Toast.makeText(DetailActivity.this, "Error::"+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
                List<Cmt> cmtrec = response.body();
                for(Cmt cmt : cmtrec){
                    mListCmt.add(cmt);
                }
                setDataAdapter(mListCmt);
                Log.e("List cmt", ""+ mListCmt.size());;

            }

            @Override
            public void onFailure(Call<List<Cmt>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Call api error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }
    private void setDataAdapter(List<Cmt> mListCmt){
        cmtAdapter = new CmtAdapter(this, mListCmt);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        rcvCon.setLayoutManager((new LinearLayoutManager(this)));
        rcvCon.setAdapter(cmtAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }




}