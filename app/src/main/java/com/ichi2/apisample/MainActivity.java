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
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ichi2.anki.api.AddContentApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    public static final String LOG_TAG = "AnkiDroidApiSample";
    private static final int AD_PERM_REQUEST = 0;

    private ListView mListView;
    private ArrayList<HashMap<String, String>> mListData;

    static EditText SharedText1;

    private String personality = "";
    private String field = "";
    private String contribution = "";
    private String criticism = "";


    int save = 0;

    private InputMethodManager imm;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm1 = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm1.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            //personality = sharedText;
            SharedText1.setText(sharedText);

            // Disable Onscreen Keyboard
            View view1 = this.getCurrentFocus(); // not working
            if (view1 != null) { // not working
                view1.clearFocus(); // not working
                imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE); // not working
                imm.hideSoftInputFromWindow(view1.getWindowToken(), 0); // not working
            }
            //Toast.makeText(MainActivity.this, "Disabled Onscreen Keyboard", Toast.LENGTH_LONG).show();

        }
    }

    public void onClick(View v) {

        //android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        //ClipData clip = clipboard.getPrimaryClip();
        //CharSequence charseq;

        //if (clip.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
        //ClipData.Item item = clip.getItemAt(0);
        //charseq = item.getText();
        //Toast.makeText(MainActivity.this,  "CharSequence : " + charseq + "\n Subsequence: " + charseq.subSequence(16,charseq.length()-2), Toast.LENGTH_LONG).show();
        //}

        switch (v.getId()) {
            /*case R.id.button:

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

                break;*/

            case R.id.button5:

                Button button5 = (Button) findViewById(R.id.button5);

                // Test Code to add basic card
                final AddContentApi api = new AddContentApi(MainActivity.this);

                // Add new deck if one doesn't already exist
                Long did = api.findDeckIdByName("Cloze");

                if (did != null) {
                    Toast.makeText(MainActivity.this, "Found Deck Cloze!", Toast.LENGTH_LONG).show();
                }

                Long mid = api.findModelIdByName("Cloze", 2);
                if (mid != null) {
                    //Toast.makeText(MainActivity.this, "Found MID Cloze!", Toast.LENGTH_LONG).show();

                }

                String cloze = SharedText1.getText().toString();
                String[] cloze_array = new String[]{cloze, "", "", ""};

                if (cloze != null) {

                    api.addNewNote(mid, did, cloze_array, "Cloze");

                    Toast.makeText(MainActivity.this, "\n Add cloze to : " + cloze, Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(MainActivity.this, "Please select some text first!!!", Toast.LENGTH_LONG).show();

                }

                break;

            case R.id.editText:
                imm.hideSoftInputFromWindow(SharedText1.getWindowToken(), 0); // not working


            default:
                Toast.makeText(MainActivity.this, "Please select some text first!!!", Toast.LENGTH_LONG).show();

        }

        save = 1;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideKeyboard(MainActivity.this); // not working

        SharedText1 = (EditText) findViewById(R.id.editText);
        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE); // not working
        imm.hideSoftInputFromWindow(SharedText1.getWindowToken(), 0); // not working


        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ichi2.apisample/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ichi2.apisample/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String send_text;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.issues:
                //Toast.makeText(MainActivity.this, "issues option selected", Toast.LENGTH_LONG).show();
                Intent intent_issues = new Intent(MainActivity.this, Issues.class);
                SharedText1 = (EditText) findViewById(R.id.editText);
                send_text = SharedText1.getText().toString();
                intent_issues.putExtra(Intent.EXTRA_TEXT, send_text);
                startActivity(intent_issues);
                return true;
            case R.id.data:
                //Toast.makeText(MainActivity.this, "data option selected", Toast.LENGTH_LONG).show();
                Intent intent_data = new Intent(MainActivity.this, Data.class);
                SharedText1 = (EditText) findViewById(R.id.editText);
                send_text = SharedText1.getText().toString();
                intent_data.putExtra(Intent.EXTRA_TEXT, send_text);
                startActivity(intent_data);
                return true;
            case R.id.definition:
                //Toast.makeText(MainActivity.this, "data option selected", Toast.LENGTH_LONG).show();
                Intent intent_definition = new Intent(MainActivity.this, Definition.class);
                SharedText1 = (EditText) findViewById(R.id.editText);
                send_text = SharedText1.getText().toString();
                intent_definition.putExtra(Intent.EXTRA_TEXT, send_text);
                startActivity(intent_definition);
                return true;
            case R.id.basic:
                //Toast.makeText(MainActivity.this, "data option selected", Toast.LENGTH_LONG).show();
                Intent intent_basic = new Intent(MainActivity.this, Basic.class);
                SharedText1 = (EditText) findViewById(R.id.editText);
                send_text = SharedText1.getText().toString();
                intent_basic.putExtra(Intent.EXTRA_TEXT, send_text);
                startActivity(intent_basic);
                return true;
            case R.id.pin:
                //Toast.makeText(MainActivity.this, "data option selected", Toast.LENGTH_LONG).show();
                Intent intent_pin = new Intent(MainActivity.this, Pin.class);
                SharedText1 = (EditText) findViewById(R.id.editText);
                send_text = SharedText1.getText().toString();
                intent_pin.putExtra(Intent.EXTRA_TEXT, send_text);
                startActivity(intent_pin);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}