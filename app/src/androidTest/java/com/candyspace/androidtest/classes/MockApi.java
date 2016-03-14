package com.candyspace.androidtest.classes;

import android.content.Context;

import com.candyspace.androidtest.api.MostPopularApi;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class MockApi implements MostPopularApi {
    Context context;

    public MockApi(Context context) {
        this.context = context;
    }

    @Override
    public void fetchArticles(Callback callback) {
        callback.onSuccess(ArticlesCreator.createArticles(context));
    }

}
