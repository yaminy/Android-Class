package com.yamin.session1.network;

import com.yamin.session1.models.LoginData;
import com.yamin.session1.models.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yamin on 10/1/2018.
 */

public interface ApiHeader {

    @GET("v2/5bb228fc330000610011c7fa")
    Call<LoginModel> login();

    @FormUrlEncoded
    @POST("api/users/signin")
    Call<LoginData> loginPerson(@Field("phone") String phone, @Field("password") String password);
}
