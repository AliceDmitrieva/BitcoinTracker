package com.alisadmitrieva.bitcointracker.ui.majorcurrencies

import com.alisadmitrieva.bitcointracker.ui.base.BaseContract

class MajorCurrenciesContract {

    interface View : BaseContract.View {
        fun showMajorCurrenciesData()
        fun showExchangeRateFragment(selectedCurrencyID: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadMajorCurrenciesData()
        fun selectCurrency(selectedCurrencyID: String)
    }

}
