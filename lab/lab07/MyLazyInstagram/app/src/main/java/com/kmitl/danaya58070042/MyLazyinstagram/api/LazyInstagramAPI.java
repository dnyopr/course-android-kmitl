package com.kmitl.danaya58070042.MyLazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student on 10/6/2017 AD.
 * กำหนดว่าจะเชื่อมต่อapiยังไงง
 * การเขียนไป mapกะข้อมูลลล เร็วด้วยง่ายด้วย
 */

public interface LazyInstagramAPI {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String userName);
}
