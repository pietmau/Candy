package com.candyspace.androidtest.classes;

import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.dagger.ApiModule;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class MockApiModuleNumbers extends ApiModule {

    @Override
    public MostPopularApi provideApi() {
        return new MockApiNumbers();
    }
}
