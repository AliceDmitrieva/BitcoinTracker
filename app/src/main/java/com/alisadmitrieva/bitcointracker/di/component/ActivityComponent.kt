package com.alisadmitrieva.bitcointracker.di.component

import com.alisadmitrieva.bitcointracker.di.module.ActivityModule
import com.alisadmitrieva.bitcointracker.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}
