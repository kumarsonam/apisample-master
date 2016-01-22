package com.ichi2.apisample;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ichi2.anki.api.AddContentApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    public static final String LOG_TAG = "AnkiDroidApiSample";
    private static final int AD_PERM_REQUEST = 0;

    private ListView mListView;
    private ArrayList<HashMap<String, String>> mListData;

    private String personality = "Personality";
    private String field = "Field";
    private String contribution = "Contribution";
    private String criticism = "Criticism";

    TextView SharedText1;


    public void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            //personality = sharedText;
            SharedText1 = (TextView) findViewById(R.id.textView);
            SharedText1.setText(sharedText);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }

            // Test Code to add basic card
        final AddContentApi api = new AddContentApi(MainActivity.this);
// Add new deck if one doesn't already exist
        Long did = api.findDeckIdByName("PIN");

        if (did != null) {
            //Toast.makeText(MainActivity.this, "Found Deck PIN!", Toast.LENGTH_LONG).show();
        }

        Long mid = api.findModelIdByName("pin", 2);
        if (mid != null) {
            //Toast.makeText(MainActivity.this, "Found MID PIN!", Toast.LENGTH_LONG).show();

        }

        Toast.makeText(MainActivity.this, "\n Personality : " + personality + "\n Field : " + field + "\n Contribution : " + contribution + "\n Criticism: " + criticism, Toast.LENGTH_LONG).show();


        //api.addNewNote(mid, did, new String[] {personality, field, contribution, criticism}, "pin");






    }


}