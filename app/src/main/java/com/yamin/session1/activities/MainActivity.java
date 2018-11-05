package com.yamin.session1.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.yamin.session1.R;
import com.yamin.session1.utils.ShowLoading;
import com.yamin.session1.models.Data;
import com.yamin.session1.models.LoginData;
import com.yamin.session1.network.APIBaseCreator;

import org.json.JSONObject;

import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Yamin on 10/1/2018.
 */

public class MainActivity extends Activity {


    LinearLayout layoutLogin;
    ProgressDialog progressDialog;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOOOKEN", " : " + token);
        layoutLogin = findViewById(R.id.layout_login);

        layoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        progressDialog = ShowLoading.showLoadingDialog(this);

        retrofit2.Call<LoginData> call = APIBaseCreator.getApiHeader().loginPerson("09358410484", "1234567890");

        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(retrofit2.Call<LoginData> call, Response<LoginData> response) {
                progressDialog.dismiss();
                Gson gson = new GsonBuilder().create();
                if (response.isSuccessful()) {
                    Data data = gson.fromJson(gson.toJson(response.body().getData()), Data.class);
//                    Toast.makeText(MainActivity.this,data.getUser().getFullName() + " خوش آمدید",Toast.LENGTH_LONG).show();
                    new StyleableToast.Builder(MainActivity.this)
                            .backgroundColor(getResources().getColor(R.color.soorati))
                            .text(data.getUser().getFullName() + " خوش آمدید")
                            .textColor(getResources().getColor(R.color.colorPrimaryDark))
                            .show();
                    Intent intent = new Intent(MainActivity.this,CreditCardsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        JSONObject jObjError2 = new JSONObject(jObjError.get("data").toString());
                        Toast.makeText(MainActivity.this,jObjError2.get("message").toString(),Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<LoginData> call, Throwable t) {
                Toast.makeText(MainActivity.this,"ارتباط با سرور برقرار نشد.",Toast.LENGTH_LONG).show();

                progressDialog.dismiss();

            }
        });
    }
}
