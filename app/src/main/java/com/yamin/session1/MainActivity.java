package com.yamin.session1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText editTextUsername, editTextPassword;
    Button btnLogin;
    SharedPreferences shared;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = getSharedPreferences("shared1",MODE_PRIVATE);
        boolean logined = shared.getBoolean("isLogined",false);
        if(logined){
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextUsername.getText().toString().equalsIgnoreCase("Yamin")
                        && editTextPassword.getText().toString().equalsIgnoreCase("1")) {

                    SharedPreferences.Editor editor = shared.edit();
                    editor.putBoolean("isLogined",true);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                    intent.putExtra("Name",editTextUsername.getText().toString());
                    intent.putExtra("Pass",editTextPassword.getText().toString());
                    startActivity(intent);


                }else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
