package com.alisadmitrieva.bitcointracker.ui.exchangerate

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alisadmitrieva.bitcointracker.R
import com.alisadmitrieva.bitcointracker.models.ExchangeRateResponse
import com.alisadmitrieva.bitcointracker.util.format

class ExchangeRateAdapter(private val context: Context,
                          private val exchangeRateDataList: List<ExchangeRateResponse>
) : RecyclerView.Adapter<ExchangeRateAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return exchangeRateDataList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val exchangeRateData = exchangeRateDataList[position]

        holder.cryptoCurrency!!.setText(exchangeRateData.cryptoCurrency)
        holder.majorCurrency!!.setText(exchangeRateData.majorCurrency)
        holder.rate!!.setText(exchangeRateData.rate.format(2))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_exchange_rate, parent, false)
        return ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cryptoCurrency = itemView.findViewById<TextView>(R.id.item_cryptocurrency)
        val majorCurrency= itemView.findViewById<TextView>(R.id.item_major_currency)
        val rate = itemView.findViewById<TextView>(R.id.item_rate)
    }

}
