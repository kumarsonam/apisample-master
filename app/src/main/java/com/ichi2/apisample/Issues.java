package com.ichi2.apisample;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ichi2.anki.api.AddContentApi;

import java.util.ArrayList;
import java.util.HashMap;

/*
public class Issues extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
*/

public class Issues extends MainActivity {
    public static final String LOG_TAG = "AnkiDroidApiSample";
    private static final int AD_PERM_REQUEST = 0;

    private ListView mListView;
    private ArrayList<HashMap<String, String>> mListData;

    private String issue = "";
    private String mnemonics = "";
    private String definition = "";
    private String for_issue = "";
    private String cases_for = "";
    private String against = "";
    private String cases_against = "";
    private String conclusion = "";
    private String evernote = "";




    //EditText SharedText1;

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

                issue = charseq.toString();
                //Toast.makeText(MainActivity.this, "\n Personality : " + personality + "\n Field : " + field + "\n Contribution : " + contribution + "\n Criticism: " + criticism, Toast.LENGTH_LONG).show();

                save = 1;
                break;


            case R.id.button2:

                Button button2 = (Button) findViewById(R.id.button2);

                //Toast.makeText(MainActivity.this, "Button2 Clicked", Toast.LENGTH_LONG).show();
                mnemonics = charseq.toString();

                save = 1;

                break;


            case R.id.button3:

                Button button3 = (Button) findViewById(R.id.button3);

                //Toast.makeText(MainActivity.this, "Button3 Clicked", Toast.LENGTH_LONG).show();
                definition = charseq.toString();

                save = 1;

                break;


            case R.id.button4:

                Button button4 = (Button) findViewById(R.id.button4);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                for_issue = charseq.toString();

                save = 1;

                break;

            case R.id.button5:

                Button button5 = (Button) findViewById(R.id.button5);

                //Toast.makeText(MainActivity.this, "Button5 Clicked", Toast.LENGTH_LONG).show();
                if (save != 1) {
                    Toast.makeText(Issues.this, "All fields empty!!!", Toast.LENGTH_LONG).show();
                    break;
                }

                // Test Code to add basic card
                final AddContentApi api = new AddContentApi(Issues.this);

                // Add new deck if one doesn't already exist
                Long did = api.findDeckIdByName("Issues");

                if (did != null) {
                    //Toast.makeText(MainActivity.this, "Found Deck PIN!", Toast.LENGTH_LONG).show();
                }

                Long mid = api.findModelIdByName("IAS", 2);
                if (mid != null) {
                    //Toast.makeText(MainActivity.this, "Found MID PIN!", Toast.LENGTH_LONG).show();

                }


                api.addNewNote(mid, did, new String[]{issue, mnemonics, definition, for_issue, cases_for, against, cases_against, conclusion, evernote}, "pin");

                Toast.makeText(Issues.this, "\n Issue : " + issue + "\n Mnemonics : " + mnemonics + "\n Definition : " + definition + "\n For: " + for_issue + "\n Cases for:" + cases_for + "\n Against:" + against + "Cases against:" + cases_against + "Conclusion: " + conclusion + "\n Evernote:" + evernote, Toast.LENGTH_LONG).show();

                break;

            case R.id.button6:

                Button button6 = (Button) findViewById(R.id.button6);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                cases_for = charseq.toString();

                save = 1;

                break;

            case R.id.button7:

                Button button7 = (Button) findViewById(R.id.button7);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                against = charseq.toString();

                save = 1;

                break;

            case R.id.button8:

                Button button8 = (Button) findViewById(R.id.button8);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                cases_against = charseq.toString();

                save = 1;

                break;

            case R.id.button9:

                Button button9 = (Button) findViewById(R.id.button9);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                conclusion = charseq.toString();

                save = 1;

                break;

            case R.id.button10:

                Button button10 = (Button) findViewById(R.id.button10);

                //Toast.makeText(MainActivity.this, "Button4 Clicked", Toast.LENGTH_LONG).show();
                evernote = charseq.toString();

                save = 1;

                break;

            case R.id.editText:
                imm.hideSoftInputFromWindow(SharedText1.getWindowToken(), 0); // not working


            default:
                Toast.makeText(Issues.this, "Please select some text first!!!", Toast.LENGTH_LONG).show();

        }

        save = 1;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);

        hideKeyboard(Issues.this); // not working


        SharedText1 = (EditText) findViewById(R.id.editText);
/*        imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE); // not working
        imm.hideSoftInputFromWindow(SharedText1.getWindowToken(), 0); // not working

*/
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        handleSendText(intent); // Handle text being sent
        /*
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }*/



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


    }
