package com.candyspace.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.RetrofitMostPopularApi;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
	@Inject
	MostPopularApi api;
	public static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Inject api dependency
		// We use this approach so that we can swap api implementation in tests
		App.inject(MainActivity.this);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		GridLayoutManager manager = new GridLayoutManager(this, 2);
		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				if(position%5==0)return 2;
				else return 1;
			}
		});

		recyclerView.setLayoutManager(manager);

		final SampleRecyclerViewAdapter adapter = new SampleRecyclerViewAdapter();
		recyclerView.setAdapter(adapter);

		api.fetchArticles(new MostPopularApi.Callback() {

			@Override
			public void onSuccess(List<Article> articles) {
				Log.d(TAG, "Got articles");
				adapter.setArticles(articles);
			}

			@Override
			public void onFailure(String error) {
				Log.d(TAG, "Failed to get articles");
			}

		});
	}
}
