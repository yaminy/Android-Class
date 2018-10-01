package com.yamin.session1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Yamin on 10/1/2018.
 */

public class MainActivity extends Activity {


    LinearLayout layoutLogin;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutLogin = findViewById(R.id.layout_login);

        layoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin() {

        retrofit2.Call<LoginModel> call = APIBaseCreator.getApiHeader().login();
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(retrofit2.Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel loginModel = response.body();
                Toast.makeText(MainActivity.this,loginModel.getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(retrofit2.Call<LoginModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"خطااا",Toast.LENGTH_LONG).show();

            }
        });
    }
}
