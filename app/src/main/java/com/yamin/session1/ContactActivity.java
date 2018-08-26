package com.yamin.session1;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.tamir7.contacts.Contact;
import com.github.tamir7.contacts.Contacts;
import com.github.tamir7.contacts.Query;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContactActivity extends AppCompatActivity {
    String name;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name = getIntent().getStringExtra("Name");
        listView = findViewById(R.id.listViewContacts);

        if (checkPermission()) {

            List<Contact> contacts = Contacts.getQuery().find();
            List<String> names = new ArrayList<>();
            for(int i = 0 ; i<contacts.size();i++){
                names.add(contacts.get(i).getDisplayName());
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.item_contact,names);
            listView.setAdapter(adapter);

        } else {
            requestPermission();
        }


    }

    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
        int sum = result + result2;
        if (result + result2 != PackageManager.PERMISSION_GRANTED) {
            return false;

        } else {


            return true;
        }
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 123);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    List<Contact> contacts = Contacts.getQuery().find();
                    Toast.makeText(ContactActivity.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ContactActivity.this,
                            "Permission denied", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}


