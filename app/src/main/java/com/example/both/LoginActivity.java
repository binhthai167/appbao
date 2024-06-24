package com.example.both;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView tvsign;
    private EditText edtUsername;
    private EditText edtPassword;
    private List<User> mListUser;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvsign = findViewById(R.id.tv_sign_up);
        edtUsername = findViewById(R.id.edt_user_name);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        mListUser = new ArrayList<>();
        getListUser();

        tvsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenSignUp();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }
    private void clickLogin() {
        String strUsername = edtUsername.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();


        if(mListUser == null || mListUser.isEmpty()){
            return;
        }
        boolean isHasUser = false;
        for(User user : mListUser){
            if(strUsername.equals(user.getName()) && strPassword.equals(user.getEmail())){
                isHasUser = true;
                break;
            }
        }
        if(isHasUser == true){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("user", strUsername);
            intent.putExtra("email",strPassword);
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "user invalid", Toast.LENGTH_SHORT).show();
        }

    }


    public void OpenSignUp(){
        Intent intent = new Intent(this, activitySignUp.class);
        startActivity(intent);
    }

    private void getListUser() {
        Api.api.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListUser = response.body();
               Log.e("List User", ""+ mListUser.size());
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
               Toast.makeText(LoginActivity.this, "Call api error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }




}