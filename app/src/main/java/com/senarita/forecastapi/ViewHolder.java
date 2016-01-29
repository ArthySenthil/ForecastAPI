package com.senarita.forecastapi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Arthy on 11/15/2015.
 */
public class ViewHolder {
    TextView txtView;
    ImageView imgView;

    public ViewHolder(View view) {
        this.txtView = (TextView) view.findViewById(R.id.textView);
        this.imgView = (ImageView) view.findViewById(R.id.imageView);
    }
}
