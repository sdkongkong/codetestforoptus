package com.kevinsong.optus.codetestoptus.utils;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;


public class RxUnitTestTools {
    private static boolean isInitRxTools = false;


    public static void openRxTools() {
        if (isInitRxTools) {
            return;
        }
        isInitRxTools = true;

        RxAndroidSchedulersHook rxAndroidSchedulersHook = new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        };

        RxJavaSchedulersHook rxJavaSchedulersHook = new RxJavaSchedulersHook() {
            @Override
            public Scheduler getIOScheduler() {
                return Schedulers.immediate();
            }
        };


        RxAndroidPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().registerSchedulersHook(rxAndroidSchedulersHook);
        RxJavaPlugins.getInstance().reset();
        RxJavaPlugins.getInstance().registerSchedulersHook(rxJavaSchedulersHook);
    }
}
