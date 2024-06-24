package com.example.both;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Queue;

import model.Cmt;
import model.Post;
import model.ResponceDelCmt;
import model.ResponeCmt;
import model.ResponeSignup;
import model.User;
import model.thoisu;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api  {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Api api = new Retrofit.Builder()
                .baseUrl("http://10.50.171.238:8000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api.class);

        @GET("users")
        Call<List<User>> getUser();

        @GET("api")
        Call<List<Post>> getPost();

        @GET("apithoisu")
        Call<List<Post>> getPostthoisu();

        @GET("apithethao")
        Call<List<Post>> getPostthethao();

        @GET("apikhoahoc")
        Call<List<Post>> getPostkhoahoc();

        @GET("apigiaoduc")
        Call<List<Post>> getPostgiaoduc();

        @GET("apigiaitri")
        Call<List<Post>> getPostgiaitri();



        @GET("cmt")
        Call<List<Cmt>> getCmt();

        @GET("addcmt")
        Call<ResponeCmt> addCmt(@Query("content") String content,
                                @Query("addby") String addby);

        @GET("regacc")
        Call<ResponeSignup> regacc(@Query("user_name") String user_name,
                                   @Query("password") String password,
                                   @Query("Cpassword") String Cpassword);
        @GET("del")
        Call<ResponceDelCmt> delCmt(@Query("content") String content);
}
