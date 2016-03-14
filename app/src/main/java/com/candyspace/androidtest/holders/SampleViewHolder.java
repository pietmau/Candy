package com.candyspace.androidtest.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.candyspace.androidtest.R;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class SampleViewHolder extends GenericHolder {

    public SampleViewHolder(View itemView) {
        super(itemView);
        isHero=false;
        image = (ImageView) itemView.findViewById(R.id.grid_item_image);
        title = (TextView) itemView.findViewById(R.id.grid_item_title);
        body = (TextView) itemView.findViewById(R.id.grid_item_body);
    }

}
