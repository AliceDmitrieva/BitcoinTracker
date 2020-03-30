package com.alisadmitrieva.bitcointracker.di.component

import com.alisadmitrieva.bitcointracker.di.module.FragmentModule
import com.alisadmitrieva.bitcointracker.ui.exchangerate.ExchangeRateFragment
import com.alisadmitrieva.bitcointracker.ui.majorcurrencies.MajorCurrenciesFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(majorCurrenciesFragment: MajorCurrenciesFragment)

    fun inject(exchangeRateFragment: ExchangeRateFragment)

}
