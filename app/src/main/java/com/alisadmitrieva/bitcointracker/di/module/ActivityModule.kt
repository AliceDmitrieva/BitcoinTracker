package com.alisadmitrieva.bitcointracker.di.module

import android.app.Activity
import com.alisadmitrieva.bitcointracker.ui.main.MainContract
import com.alisadmitrieva.bitcointracker.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}
