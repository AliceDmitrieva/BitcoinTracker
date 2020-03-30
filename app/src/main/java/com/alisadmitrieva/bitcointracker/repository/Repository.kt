package com.alisadmitrieva.bitcointracker.repository

import com.alisadmitrieva.bitcointracker.api.APIService
import com.alisadmitrieva.bitcointracker.models.ExchangeRateResponse
import com.alisadmitrieva.bitcointracker.models.CommonExchangeRateModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: APIService) {
    fun showDataFromAPI(selectedCurrency: String?) =
        Observable.zip(
                apiService.getExchangeRate("BTC", selectedCurrency).subscribeOn(Schedulers.io()),
                apiService.getExchangeRate("ETH", selectedCurrency).subscribeOn(Schedulers.io()),
                apiService.getExchangeRate("LTC", selectedCurrency).subscribeOn(Schedulers.io()),
                Function3<ExchangeRateResponse, ExchangeRateResponse, ExchangeRateResponse, CommonExchangeRateModel>
                { btcExchangeRateResponse, ethExchangeRateResponse, ltcExchangeRateResponse ->
                    createCommonExchangeRateModel(btcExchangeRateResponse, ethExchangeRateResponse, ltcExchangeRateResponse)
                })
            .observeOn(AndroidSchedulers.mainThread())

    private fun createCommonExchangeRateModel(btcExchangeRateResponse: ExchangeRateResponse,
                                              ethExchangeRateResponse: ExchangeRateResponse,
                                              ltcExchangeRateResponse: ExchangeRateResponse): CommonExchangeRateModel {

        return CommonExchangeRateModel(listOf(btcExchangeRateResponse, ethExchangeRateResponse, ltcExchangeRateResponse))
    }

}
