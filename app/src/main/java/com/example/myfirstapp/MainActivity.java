package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This activity lets the user input a list of vegetables in a text box and then launches
 * another activity to search recipe database based on listed vegetables.2
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String INGREDIENT_SEARCH_STRING = "http://192.168.1.18:8983/solr/cheemucore/select?q=";

    private ListView recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void sendMessage(View view) {
        hideKeyboard(this);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        String encodedMessage = URLEncoder.encode(message);
        String url = INGREDIENT_SEARCH_STRING + encodedMessage;
    }
}