package com.example.android.newsapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Create a new {@link NewsAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param arrayList is the list of {@link News}s to be displayed.
     */
    public NewsAdapter(Context context,ArrayList<News> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        News currentWord = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);

        titleTextView.setText(currentWord.getTitle());

        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description);

        descriptionTextView.setText(currentWord.getDescription());

        ImageView imageView = listItemView.findViewById(R.id.imageview);

        Picasso.get()
                .load(currentWord.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);


        return listItemView;
    }
}
