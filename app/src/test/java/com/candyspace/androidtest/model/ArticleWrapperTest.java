package com.candyspace.androidtest.model;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.ArticleDeserializer;
import com.candyspace.androidtest.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleWrapperTest {
	private Response response;
	public static final String THUMB_URL="http://a/thumb/url";
	public static final String HERO_URL="http://a/hero/url";

	@Mock Article article;

	ArticleWrapper articleWrapper;

	@Before
	public void setUp() throws Exception {
		articleWrapper = new ArticleWrapper(article);
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("response_two");
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Article.class, new ArticleDeserializer())
				.create();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		response = gson.fromJson(reader, Response.class);
	}

	@Test
	public void testGetThumbImageUrl() throws Exception {
		List<Article.Media> list=new ArrayList<>();
		Article.Media media=new Article.Media();
		List<Article.Media.MediaMetadata> metadata=new ArrayList<>();
		Article.Media.MediaMetadata mediaMetadata= new Article.Media.MediaMetadata();
		mediaMetadata.setFormat("Large Thumbnail");
		mediaMetadata.setUrl(THUMB_URL);
		metadata.add(mediaMetadata);
		media.setMediaMetadataList(metadata);
		media.setType("image");
		list.add(media);
		Mockito.when(article.getMediaList()).thenReturn(list);
		assertEquals(THUMB_URL, articleWrapper.getThumbImageUrl());
	}

	@Test
	public void testGetHeroImageUrl() throws Exception {
		List<Article.Media> list=new ArrayList<>();
		Article.Media media=new Article.Media();
		List<Article.Media.MediaMetadata> metadata=new ArrayList<>();
		Article.Media.MediaMetadata mediaMetadata= new Article.Media.MediaMetadata();
		mediaMetadata.setFormat("square640");
		mediaMetadata.setUrl(HERO_URL);
		metadata.add(mediaMetadata);
		media.setMediaMetadataList(metadata);
		media.setType("image");
		list.add(media);
		Mockito.when(article.getMediaList()).thenReturn(list);
		assertEquals(HERO_URL, articleWrapper.getHeroImageUrl());
	}

	@Test
	public void testExpectNotEmpyImages(){
		Article article=response.getResults().get(0);
		ArticleWrapper articleWrapper= new ArticleWrapper(article);
		assertNotNull(articleWrapper.getHeroImageUrl());
		assertNotNull(articleWrapper.getThumbImageUrl());
	}

	/**
	 * If an article contains no media, RetrofitMostPopularApi discards it,
	 * therefore it will never be passed to the wrapper
	 * and we will never be in this situation, where the wrapper throws a NPE
	 */
	@Test (expected = NullPointerException.class)
	public void testExpectEmpyImages(){
		Article article=response.getResults().get(1);
		ArticleWrapper articleWrapper= new ArticleWrapper(article);
		assertNotNull(articleWrapper.getHeroImageUrl());
		assertNotNull(articleWrapper.getThumbImageUrl());
	}
}