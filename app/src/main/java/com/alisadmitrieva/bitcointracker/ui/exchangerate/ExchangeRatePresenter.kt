package com.alisadmitrieva.bitcointracker.ui.exchangerate

import com.alisadmitrieva.bitcointracker.api.APIService
import com.alisadmitrieva.bitcointracker.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ExchangeRatePresenter @Inject constructor(apiService: APIService) : ExchangeRateContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: ExchangeRateContract.View
    private var repository = Repository(apiService)

    override fun attach(view: ExchangeRateContract.View) {
        this.view = view
    }

    override fun loadExchangeRateData(selectedCurrency: String?) {

        subscriptions.add(
            repository.showDataFromAPI(selectedCurrency)
                .subscribe({
                    view.showProgress(false)
                    view.showExchangeRateData(it)
                }, { error ->
                    view.showProgress(false)
                    view.showMessageError(error.localizedMessage)
                })
        )
    }

}
