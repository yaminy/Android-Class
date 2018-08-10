package com.yamin.session1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    String buttonTxt = "Button Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn1);

        button.setText(buttonTxt);

//        setColor(bbbb);

    }

//    void setColor(Button bndfd) {
////        bndfd.setBackgroundColor(R.color.colorAccent);
//    }

}
