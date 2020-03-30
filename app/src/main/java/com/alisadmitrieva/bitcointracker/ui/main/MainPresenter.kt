package com.alisadmitrieva.bitcointracker.ui.main

class MainPresenter : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showMajorCurrenciesFragment()
    }

}
