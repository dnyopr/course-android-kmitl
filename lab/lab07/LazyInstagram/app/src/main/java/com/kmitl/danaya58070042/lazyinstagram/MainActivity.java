package com.kmitl.danaya58070042.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kmitl.danaya58070042.lazyinstagram.adapter.PostAdapter;
import com.kmitl.danaya58070042.lazyinstagram.api.LazyInstagramAPI;
import com.kmitl.danaya58070042.lazyinstagram.api.UserProfile;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("android");

        PostAdapter postAdapter = new PostAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(final String userName) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramAPI.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramAPI lazyInstagramAPI = retrofit.create(LazyInstagramAPI.class);

//        Call<String> call = lazyInstagramAPI.getProfile(userName);
//        call.enqueue(new Callback<String>() { /*พิมถึงเน้*/
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String result = response.body();
//                TextView textName = (TextView) findViewById(R.id.textname);
//                textName.setText(result);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });

        Call<UserProfile> call = lazyInstagramAPI.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    TextView textName = (TextView) findViewById(R.id.textName);
                    textName.setText("@" + userProfile.getUser());

                    ImageView imageUser = (ImageView) findViewById(R.id.imageUser);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageUser);
                    ;
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
