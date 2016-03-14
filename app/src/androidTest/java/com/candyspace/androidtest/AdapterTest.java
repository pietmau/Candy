package com.candyspace.androidtest;

import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.candyspace.androidtest.api.dagger.ApiModule;
import com.candyspace.androidtest.api.dagger.DaggerApiComponent;
import com.candyspace.androidtest.classes.ArticlesCreator;
import com.candyspace.androidtest.classes.MockApiModule;
import com.candyspace.androidtest.classes.MockApiModuleNumbers;
import com.robotium.solo.Solo;

public class AdapterTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public AdapterTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testAdapter() throws InterruptedException {
        RecyclerView recyclerView = new RecyclerView(solo.getCurrentActivity());
        SampleRecyclerViewAdapter sampleRecyclerViewAdapter = new SampleRecyclerViewAdapter();
        recyclerView.setAdapter(sampleRecyclerViewAdapter);
        sampleRecyclerViewAdapter.setArticles(ArticlesCreator.createArticles(solo.getCurrentActivity()));
        int type = sampleRecyclerViewAdapter.getItemViewType(0);
        assertTrue(type == SampleRecyclerViewAdapter.HERO);
        assertTrue(type != SampleRecyclerViewAdapter.NORMAL);

        type = sampleRecyclerViewAdapter.getItemViewType(5);
        assertTrue(type == SampleRecyclerViewAdapter.HERO);
        assertTrue(type != SampleRecyclerViewAdapter.NORMAL);


        type = sampleRecyclerViewAdapter.getItemViewType(19);
        assertTrue(type == SampleRecyclerViewAdapter.NORMAL);
        assertTrue(type != SampleRecyclerViewAdapter.HERO);

    }

    public void testAdapter2() throws InterruptedException {
        RecyclerView recyclerView = new RecyclerView(solo.getCurrentActivity());
        SampleRecyclerViewAdapter sampleRecyclerViewAdapter = new SampleRecyclerViewAdapter();
        recyclerView.setAdapter(sampleRecyclerViewAdapter);
        sampleRecyclerViewAdapter.setArticles(ArticlesCreator.createArticles(solo.getCurrentActivity()));
        RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(0);
        // Must be null because the recycler is not drawn
        assertNull(holder);
    }

}