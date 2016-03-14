package com.candyspace.androidtest.model;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.ArticleDeserializer;
import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class ApiTest {

    @Test
    public void testError(){
        MostPopularApi.Callback callback = Mockito.mock(MostPopularApi.Callback.class);
        MostPopularApi mostPopularApi=Mockito.mock(MostPopularApi.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((MostPopularApi.Callback)invocation.getArguments()[0]).onFailure("Maurizio Error");
                return null;
            }
        }).when(mostPopularApi).fetchArticles(callback);

        mostPopularApi.fetchArticles(callback);
        Mockito.verify(callback,times(1)).onFailure("Maurizio Error");
        Mockito.verify(callback,times(0)).onSuccess(any(List.class));
    }

    @Test
    public void testSuccess(){
        MostPopularApi.Callback callback = Mockito.mock(MostPopularApi.Callback.class);
        MostPopularApi mostPopularApi=Mockito.mock(MostPopularApi.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                List<Article> articles=new ArrayList<Article>();
                ((MostPopularApi.Callback)invocation.getArguments()[0]).onSuccess(articles);
                return null;
            }
        }).when(mostPopularApi).fetchArticles(callback);

        mostPopularApi.fetchArticles(callback);
        Mockito.verify(callback,times(0)).onFailure(anyString());
        Mockito.verify(callback,times(1)).onSuccess(any(List.class));
    }

    @Test
    public void testSuccessWithResources(){
        MostPopularApi.Callback callback = Mockito.mock(MostPopularApi.Callback.class);
        MostPopularApi mostPopularApi=Mockito.mock(MostPopularApi.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                List<Article> articles= getArticlesFromResources();
                ((MostPopularApi.Callback)invocation.getArguments()[0]).onSuccess(articles);
                return null;
            }
        }).when(mostPopularApi).fetchArticles(callback);

        mostPopularApi.fetchArticles(callback);
        Mockito.verify(callback,times(0)).onFailure(anyString());
        Mockito.verify(callback,times(1)).onSuccess(any(List.class));
    }

    private List<Article> getArticlesFromResources(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("response_two");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Article.class, new ArticleDeserializer())
                .create();
        Response response = gson.fromJson(new BufferedReader(new InputStreamReader(in)), Response.class);
        return response.getResults();
    }
}
