package com.sup.mehandi;

import android.app.Application;

/**
 * Created by Umakant Angadi on 12/24/2016.
 */
public class App  extends Application{
    static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

}
