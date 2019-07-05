package com.google.herykuswanto.myfeeds.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.google.herykuswanto.myfeeds.Model.Data;
import com.google.herykuswanto.myfeeds.R;
import com.google.herykuswanto.myfeeds.Rest.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView id,user_name,thumbnail_image,posted_date,post_desc,post_image,likes_count,comments_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id= findViewById(R.id.id);
        user_name= findViewById(R.id.user_name);
        thumbnail_image= findViewById(R.id.thumbnail_image);
        posted_date= findViewById(R.id.posted_date);
        post_desc= findViewById(R.id.post_desc);
        post_image= findViewById(R.id.post_image);
        likes_count= findViewById(R.id.likes_count);
        comments_count= findViewById(R.id.comments_count);

        getSampleJsonResponse();
    }


    private void getSampleJsonResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-18ca8-kikirizkijulian1.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface=retrofit.create(RequestInterface.class);

        Call<Data> call;
        call = requestInterface.getData();

        call.enqueue(new Callback<Data>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful())

                   id.setText("id : "+response.body().getId());
                    user_name.setText("user_name : "+response.body().getUserName());
                    thumbnail_image.setText("thumbnail_image: "+response.body().getThumbnailImage());
                     posted_date.setText("posted_date: "+response.body().getPostedDate());
                     post_desc.setText("post_desc: "+response.body().getPostDesc());
                    post_image.setText("post_image: "+response.body().getPostImage());
                    likes_count.setText("likes_count: "+response.body().getLikesCount());
                    comments_count.setText("comments_count: "+response.body().getCommentsCount());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
