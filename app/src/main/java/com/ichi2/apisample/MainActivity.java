package com.ichi2.apisample;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
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
import android.text.ClipboardManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
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

    EditText SharedText1;

    int save = 0;


    public void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            //personality = sharedText;
            SharedText1 = (EditText) findViewById(R.id.editText);
            SharedText1.setText(sharedText);
        }
    }

    public void onClick(View v) {

        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        CharSequence charseq;

        //if (clip.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            ClipData.Item item = clip.getItemAt(0);
            charseq = item.getText();
            //Toast.makeText(MainActivity.this,  "CharSequence : " + charseq + "\n Subsequence: " + charseq.subSequence(16,charseq.length()-2), Toast.LENGTH_LONG).show();
        //}

        switch (v.getId()) {
            case R.id.button:

                Button button = (Button) findViewById(R.id.button);

                //Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_LONG).show();

                personality = charseq.toString();
                //Toast.makeText(MainActivity.this, "\n Personality : " + personality + "\n Field : " + field + "\n Contribution : " + contribution + "\n Criticism: " + criticism, Toast.LENGTH_LONG).show();

                save = 1;
                break;


            case R.id.button2:

                Button button2 = (Button) findViewById(R.id.button2);

                //Toast.makeText(MainActivity.this, "Button2 Clicked", Toast.LENGTH_LONG).show();
                field = charseq.toString();

                save = 1;

                break;


            case R.id.button3:

                Button button3 = (Button) findViewById(R.id.button3);

                //Toast.makeText(MainActivity.this, "Button3 Clicked", Toast.LENGTH_LONG).show();
                contribution = charseq.toString();

                save = 1;

                break;


            case R.id.button4:

                Button button4 = (Button) findViewById(R.id.button4);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                criticism = charseq.toString();

                save = 1;

                break;

            case R.id.button5:

                Button button5 = (Button) findViewById(R.id.button5);

                //Toast.makeText(MainActivity.this, "Button5 Clicked", Toast.LENGTH_LONG).show();
                if (save != 1) {
                    Toast.makeText(MainActivity.this, "All fields empty!!!", Toast.LENGTH_LONG).show();
                    break;
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


                api.addNewNote(mid, did, new String[] {personality, field, contribution, criticism}, "pin");

                Toast.makeText(MainActivity.this, "\n Personality : " + personality + "\n Field : " + field + "\n Contribution : " + contribution + "\n Criticism: " + criticism, Toast.LENGTH_LONG).show();

                break;

            default:
                Toast.makeText(MainActivity.this, "Please select some text first!!!", Toast.LENGTH_LONG).show();

        }

        save = 1;

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







    }


}