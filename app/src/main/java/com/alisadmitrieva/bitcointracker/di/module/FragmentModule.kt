package com.alisadmitrieva.bitcointracker.di.module

import com.alisadmitrieva.bitcointracker.api.APIService
import com.alisadmitrieva.bitcointracker.ui.exchangerate.ExchangeRateContract
import com.alisadmitrieva.bitcointracker.ui.exchangerate.ExchangeRatePresenter
import com.alisadmitrieva.bitcointracker.ui.majorcurrencies.MajorCurrenciesContract
import com.alisadmitrieva.bitcointracker.ui.majorcurrencies.MajorCurrenciesPresenter
import com.alisadmitrieva.bitcointracker.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class FragmentModule {

    @Provides
    fun provideMajorCurrenciesPresenter(): MajorCurrenciesContract.Presenter {
        return MajorCurrenciesPresenter()
    }

    @Provides
    fun provideExchangeRatePresenter(apiService: APIService): ExchangeRateContract.Presenter {
        return ExchangeRatePresenter(apiService)
    }

    @Provides
    fun create(): APIService {
        val retrofit = retrofit2.Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
        return retrofit.create(APIService::class.java)
    }

}
