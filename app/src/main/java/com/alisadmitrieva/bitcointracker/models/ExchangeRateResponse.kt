package com.alisadmitrieva.bitcointracker.models

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(
    @SerializedName("asset_id_base")
    val cryptoCurrency: String,
    @SerializedName("asset_id_quote")
    val majorCurrency: String,
    @SerializedName("rate")
    val rate: Double
)
