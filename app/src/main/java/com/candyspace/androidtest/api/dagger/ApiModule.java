package com.candyspace.androidtest.api.dagger;

import com.candyspace.androidtest.api.MostPopularApi;
import com.candyspace.androidtest.api.RetrofitMostPopularApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
@Module
public class ApiModule  {
    @Provides
    @Singleton
    public MostPopularApi provideApi(){
        return new RetrofitMostPopularApi();
    }

}
