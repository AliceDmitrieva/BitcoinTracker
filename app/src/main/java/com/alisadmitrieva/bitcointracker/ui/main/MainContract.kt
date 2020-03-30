package com.alisadmitrieva.bitcointracker.ui.main

import com.alisadmitrieva.bitcointracker.ui.base.BaseContract

class MainContract {

    interface View : BaseContract.View {
        fun showMajorCurrenciesFragment()
    }

    interface Presenter : BaseContract.Presenter<View>

}
