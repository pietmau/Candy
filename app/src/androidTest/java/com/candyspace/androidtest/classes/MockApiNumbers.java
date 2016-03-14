package com.candyspace.androidtest.classes;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.MostPopularApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class MockApiNumbers implements MostPopularApi {

    @Override
    public void fetchArticles(Callback callback) {
        callback.onSuccess(createArticles());
    }

    private List<Article> createArticles() {
        List<Article> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Article article = new Article();
            article.setTitle("Title " + i);
            article.setBody("Body " + i);
            List<Article.Media> medialist = new ArrayList<>();
            Article.Media media = new Article.Media();
            media.setType("foo");
            medialist.add(media);
            article.setMediaList(medialist);
            list.add(article);
        }
        return list;
    }
}
