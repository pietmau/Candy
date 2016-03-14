package com.candyspace.androidtest;

import android.app.Application;

import com.candyspace.androidtest.api.dagger.ApiComponent;
import com.candyspace.androidtest.api.dagger.ApiModule;
import com.candyspace.androidtest.api.dagger.DaggerApiComponent;

import javax.inject.Inject;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class App extends Application {
    @Inject
    public static ApiComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApiComponent.builder().apiModule(new ApiModule()).build().inject(this);
    }

    public static void inject(MainActivity mainActivity) {
        injector.inject(mainActivity);
    }

}
