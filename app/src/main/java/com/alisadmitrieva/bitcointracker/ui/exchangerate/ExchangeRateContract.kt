package com.alisadmitrieva.bitcointracker.ui.exchangerate

import com.alisadmitrieva.bitcointracker.models.CommonExchangeRateModel
import com.alisadmitrieva.bitcointracker.ui.base.BaseContract

class ExchangeRateContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showExchangeRateData(commonExchangeRateModel: CommonExchangeRateModel)
        fun showMessageError(errorMessage: String?)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadExchangeRateData(selectedCurrency: String?)
    }

}
