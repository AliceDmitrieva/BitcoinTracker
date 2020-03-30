package com.alisadmitrieva.bitcointracker.ui.majorcurrencies

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alisadmitrieva.bitcointracker.R

class MajorCurrenciesAdapter(private val context: Context,
                             private val majorCurrenciesArray: Array<String>,
                             fragment: Fragment
) : RecyclerView.Adapter<MajorCurrenciesAdapter.ListViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }

    override fun getItemCount(): Int {
        return majorCurrenciesArray.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val majorCurrencyID = majorCurrenciesArray[position]

        holder.title!!.setText(majorCurrencyID)
        holder.layout!!.setOnClickListener {
            listener.itemDetail(majorCurrencyID)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_major_currencies, parent, false)
        return ListViewHolder(itemView)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_title)
    }

    interface onItemClickListener {
        fun itemDetail(selectedCurrencyID: String)
    }

}
