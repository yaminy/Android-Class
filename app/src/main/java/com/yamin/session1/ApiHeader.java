package com.yamin.session1;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Yamin on 9/24/2018.
 */

public interface ApiHeader {

    @GET(".")
    Call<GithubResponse> getResponse();


}
