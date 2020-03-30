package com.alisadmitrieva.bitcointracker.api

import com.alisadmitrieva.bitcointracker.models.ExchangeRateResponse
import com.alisadmitrieva.bitcointracker.util.Constants.Companion.API_KEY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("v1/exchangerate/{cryptoCurrency}/{majorCurrency}?apikey=${API_KEY}")
    fun getExchangeRate(
        @Path("cryptoCurrency") cryptoCurrencyID: String,
        @Path("majorCurrency") majorCurrencyID: String?
    ): Observable<ExchangeRateResponse>

}
