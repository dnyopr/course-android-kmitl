package com.kmitl.danaya58070042.MyLazyinstagram;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kmitl.danaya58070042.MyLazyinstagram.adapter.PostAdapter;
import com.kmitl.danaya58070042.lazyinstagram.R;
import com.kmitl.danaya58070042.MyLazyinstagram.api.LazyInstagramAPI;
import com.kmitl.danaya58070042.MyLazyinstagram.api.UserProfile;
import com.kmitl.danaya58070042.MyLazyinstagram.fragment.MainFragment;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInterfaceListener {


    private String username = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserProfile(username);


//        Button switchUserBtn = (Button) findViewById(R.id.switch_user_btn);
//
//        switchUserBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager
//                        .beginTransaction()
//                        .add(R.id.fragmentContainer, new MainFragment().newInstance("Activity From Access"))
//                        .commit();
//            }
//        });

    }


    public void getUserProfile(final String userName) {
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
                    showProfile(call, response);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    String[] account = {"android", "cartoon", "nature"};

    public void onSwitchAccount(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(account, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = account[which];
                getUserProfile(user);
            }
        });
        builder.show();

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.choose_acc_fragment, new MainFragment())
//                .addToBackStack("choose an acc")
//                .commit();

    }

    public void showProfile(Call<UserProfile> call, Response<UserProfile> response) {
        UserProfile userProfile = response.body();

        PostAdapter postAdapter = new PostAdapter(this, userProfile.getPosts());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);

        TextView textName = (TextView) findViewById(R.id.textName);
        textName.setText("@" + userProfile.getUser());

        ImageView imageUser = (ImageView) findViewById(R.id.imageUser);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageUser);

        TextView post = (TextView) findViewById(R.id.post_amount);
        post.setText(String.valueOf(userProfile.getPost()));
        TextView following = (TextView) findViewById(R.id.following_amount);
        following.setText(String.valueOf(userProfile.getFollowing()));
        TextView follower = (TextView) findViewById(R.id.follower_amount);
        follower.setText(String.valueOf(userProfile.getFollower()));
        TextView bio = (TextView) findViewById(R.id.bio);
        bio.setText(userProfile.getBio());

    }


}
