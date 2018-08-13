package com.yamin.session1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber1, editTextNumber2;
    Button buttonAdd;
    TextView textViewSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inItView();

        handleOperation();


    }

    private void handleOperation() {

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edit1 = editTextNumber1.getText().toString();
                String edit2 = editTextNumber2.getText().toString();

                textViewSum.setText(Integer.valueOf(edit1) + Integer.valueOf(edit2));

            }
        });

    }

    private void inItView() {
        editTextNumber1 = (EditText) findViewById(R.id.editTxt_number_1);
        editTextNumber2 = (EditText) findViewById(R.id.editTxt_number_2);
        buttonAdd = (Button) findViewById(R.id.button_add);
        textViewSum = (TextView) findViewById(R.id.txtview_sum);
    }


}
