package com.candyspace.androidtest.api.dagger;

import com.candyspace.androidtest.App;
import com.candyspace.androidtest.MainActivity;
import com.candyspace.androidtest.api.MostPopularApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 *
 */
@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {

    void inject(MainActivity mainActivity);

    MostPopularApi provideApi();

    void inject(App app);
}
