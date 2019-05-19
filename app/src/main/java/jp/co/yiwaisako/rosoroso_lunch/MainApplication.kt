package jp.co.yiwaisako.rosoroso_lunch

import android.support.multidex.MultiDexApplication
import timber.log.Timber

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}