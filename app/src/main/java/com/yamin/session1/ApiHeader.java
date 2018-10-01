package com.yamin.session1;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Yamin on 10/1/2018.
 */

public interface ApiHeader {

    @GET("v2/5bb228fc330000610011c7fa")
    Call<LoginModel> login();
}
