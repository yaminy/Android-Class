package com.yamin.session1.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.yamin.session1.R;
import com.yamin.session1.models.CreditCard;

public class CreditCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_cards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                Intent intent = new Intent(CreditCardsActivity.this,AddCreditActivity.class);
//                startActivity(intent);

                openInsertDialog();
            }
        });
    }
    AlertDialog dialog;
    private void openInsertDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout, (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content), false);


        dialog = new AlertDialog.Builder(this).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button addBtn = view.findViewById(R.id.add_button);
        final android.support.v7.widget.AppCompatEditText placeName = view.findViewById(R.id.name_offer_place);
        final android.support.v7.widget.AppCompatEditText placeDesc = view.findViewById(R.id.desc_offer_place);
        Button abortBtn = view.findViewById(R.id.abort_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditCard  creditCard = new CreditCard(placeName.getText().toString(),placeDesc.getText().toString());

            }
        });
        abortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }



}
