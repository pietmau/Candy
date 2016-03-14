package com.candyspace.androidtest.model;

import com.candyspace.androidtest.R;
import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.ArticleDeserializer;
import com.candyspace.androidtest.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class DeserializerTest {

    private Response response;

    @Before
    public void setUp() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("response_two");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Article.class, new ArticleDeserializer())
                .create();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        response = gson.fromJson(reader, Response.class);
    }

    @Test
    public void test(){
        assertNotNull(response);
    }
    @Test
    public void testExpectEmpyMedia(){
       //We expect second element not to have media
        assertNull(response.getResults().get(1).getMediaList());
        //Litmus
        assertTrue(response.getResults().get(1).getMediaList() == null);
    }
    @Test
    public void testExpectNotEmptyMedia(){
        //We expect second element not to have media
        assertNotNull(response.getResults().get(0).getMediaList());
        assertTrue(response.getResults().get(0).getMediaList() != null);
    }

}
