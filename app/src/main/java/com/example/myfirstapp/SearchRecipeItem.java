package com.example.myfirstapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeItem {
    private static final String TAG = "SearchRecipeItem";
    public String title;
    public Integer recipeImg = R.drawable.aloo_jira;
    public String directions;

    public SearchRecipeItem(String title, String directions) {
        this.title = title;
        this.directions = directions;
    }


    public static List<SearchRecipeItem> parseSearchResult(String in) throws JSONException {
        JSONObject reader = new JSONObject(in);
        JSONObject response = reader.getJSONObject("response");
        JSONArray docs  = response.getJSONArray("docs");
        List<SearchRecipeItem> recipeTitles = new ArrayList<SearchRecipeItem>();
        for (int i = 0; i < docs.length(); i++) {
            Log.i(TAG, "Start Parsing item " + i);
            JSONObject item = docs.getJSONObject(i);
            JSONArray title = item.getJSONArray("title");
            JSONArray directions = item.getJSONArray("directions");
            String itemTitle;
            String itemDirections = "";

            if (title != null) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < title.length(); j++) {
                    sb.append(title.getString(j));
                    sb.append(" ");
                }

                itemTitle = sb.toString();
                Log.i(TAG, "title=" + itemTitle);
            } else {
                Log.e(TAG, "Missing item title. Ignoring item " + i);
                continue;
            }

            if (directions != null) {
                StringBuilder sb2 = new StringBuilder();
                for (int k = 0; k < directions.length(); k++) {
                    sb2.append(directions.getString(k));
                    sb2.append(" ");
                }

                itemDirections = sb2.toString();
                Log.i(TAG, "directions=" + itemDirections);
            } else {
                Log.w(TAG, "Missing item directions " + i + " displaying empty string and continuing anyway");
            }
            recipeTitles.add(new SearchRecipeItem(itemTitle, itemDirections));
            Log.i(TAG, "End Parsing item " + i);
        }
        return recipeTitles;
    }
}
