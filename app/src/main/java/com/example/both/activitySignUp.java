package com.example.both;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.ResponeSignup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activitySignUp extends AppCompatActivity {
    private TextView edtusername;
    private TextView edtpw;
    private TextView edtconfirmpw;
    private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtusername = findViewById(R.id.edt_usernamesu);
        edtpw = findViewById(R.id.edt_pwsu);
        edtconfirmpw = findViewById(R.id.edt_confirmpw);
        btn_signup = findViewById(R.id.btn_signup);
        Init();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activitySignUp.this, edtusername.getText()+" "+edtpw.getText()+" "+edtconfirmpw.getText(), Toast.LENGTH_SHORT).show();
                Call<ResponeSignup> i = Api.api.regacc(String.valueOf(edtusername.getText()),String.valueOf(edtpw.getText()),String.valueOf(edtconfirmpw.getText()));
                i.enqueue(new Callback<ResponeSignup>() {
                    @Override
                    public void onResponse(Call<ResponeSignup> call, Response<ResponeSignup> response) {
                        Toast.makeText(activitySignUp.this, response.body().getMess(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponeSignup> call, Throwable t) {

                    }
                });

            }
        });






    }
    public void Init(){
        edtusername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    edtusername.setError("enter username!");}


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        edtpw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() <6){
                    edtpw.setError("\n" +
                            "Password must be more than 6 characters");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtconfirmpw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(edtconfirmpw.getText().toString().trim().equals(edtpw.getText().toString().trim())){
                    edtconfirmpw.setError(null);
                }else edtconfirmpw.setError("");
            }
        });



    }
}