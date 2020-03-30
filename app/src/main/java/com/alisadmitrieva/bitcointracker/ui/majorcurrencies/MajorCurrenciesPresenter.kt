package com.alisadmitrieva.bitcointracker.ui.majorcurrencies

class MajorCurrenciesPresenter : MajorCurrenciesContract.Presenter {

    private lateinit var view: MajorCurrenciesContract.View

    override fun attach(view: MajorCurrenciesContract.View) {
        this.view = view
    }

    override fun loadMajorCurrenciesData() {
        view.showMajorCurrenciesData()
    }

    override fun selectCurrency(selectedCurrencyID: String) {
        view.showExchangeRateFragment(selectedCurrencyID)
    }

}
