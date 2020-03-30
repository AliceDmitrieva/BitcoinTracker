package com.alisadmitrieva.bitcointracker.di.component

import com.alisadmitrieva.bitcointracker.BaseApp
import com.alisadmitrieva.bitcointracker.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent {

    fun inject(application: BaseApp)

}
