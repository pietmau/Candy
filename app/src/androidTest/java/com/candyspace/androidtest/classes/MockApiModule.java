package com.candyspace.androidtest.classes;

import android.content.Context;

import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.dagger.ApiModule;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class MockApiModule extends ApiModule {
    private Context context;

    public MockApiModule(Context context) {
        this.context = context;
    }

    @Override
    public MostPopularApi provideApi() {
        return new MockApi(context);
    }
}
