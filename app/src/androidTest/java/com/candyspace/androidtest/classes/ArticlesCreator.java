package com.candyspace.androidtest.classes;

import android.content.Context;

import com.candyspace.androidtest.R;
import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.ArticleDeserializer;
import com.candyspace.androidtest.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class ArticlesCreator {
    public static List<Article> createArticles(Context context) {
        InputStream in = context.getResources().openRawResource(R.raw.response);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Article.class, new ArticleDeserializer())
                .create();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Response response = gson.fromJson(reader, Response.class);
        return response.getResults();
    }
}
