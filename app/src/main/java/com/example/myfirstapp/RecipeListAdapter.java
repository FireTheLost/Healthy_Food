package com.example.myfirstapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecipeListAdapter extends ArrayAdapter<SearchRecipeItem> {
    private final Activity context;
    private final List<SearchRecipeItem> recipeItems;

    public RecipeListAdapter(Activity context, List<SearchRecipeItem> recipeItems) {
        super(context, R.layout.recipe_item, recipeItems);

        this.context=context;
        this.recipeItems = recipeItems;
    }

    private static class ViewHolder {
        TextView recipeTitle;
        ImageView recipeImg;
        TextView recipeDirections;
    }

    public View getView(int position,View rowView,ViewGroup parent) {
        ViewHolder holder;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.recipe_item, null, true);
            holder = new ViewHolder();
            holder.recipeTitle = (TextView) rowView.findViewById(R.id.recipe_title);
            holder.recipeImg = (ImageView) rowView.findViewById(R.id.recipe_img);
            holder.recipeDirections = (TextView) rowView.findViewById(R.id.recipe_desc);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.recipeTitle.setText(recipeItems.get(position).title);
        holder.recipeImg.setImageResource(recipeItems.get(position).recipeImg);
        holder.recipeDirections.setText(recipeItems.get(position).directions);

        return rowView;
    };
}