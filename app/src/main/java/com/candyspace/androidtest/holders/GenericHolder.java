package com.candyspace.androidtest.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 *
 * We use this interface so that our adapter cans use both hero and normal holders
 */
public abstract class GenericHolder extends RecyclerView.ViewHolder {
    public boolean isHero;
    public ImageView image;
    public TextView title;
    public TextView body;
    
    public GenericHolder(View itemView) {
        super(itemView);
    }
}
