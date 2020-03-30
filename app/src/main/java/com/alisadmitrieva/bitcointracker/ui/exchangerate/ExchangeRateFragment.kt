package com.alisadmitrieva.bitcointracker.ui.exchangerate

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alisadmitrieva.bitcointracker.R
import com.alisadmitrieva.bitcointracker.di.component.DaggerFragmentComponent
import com.alisadmitrieva.bitcointracker.di.module.FragmentModule
import com.alisadmitrieva.bitcointracker.models.CommonExchangeRateModel
import kotlinx.android.synthetic.main.fragment_exchange_rate.*
import javax.inject.Inject

class ExchangeRateFragment : Fragment(), ExchangeRateContract.View {

    @Inject
    lateinit var presenter: ExchangeRateContract.Presenter
    private lateinit var rootView: View
    private var SELECTED_CURRENCY_ARGUMENT = "SELECTED_CURRENCY"

    fun newInstance(selectedCurrency: String): ExchangeRateFragment {
        val exchangeRateFragment = ExchangeRateFragment()
        val bundle = Bundle()
        bundle.putString(SELECTED_CURRENCY_ARGUMENT, selectedCurrency)
        exchangeRateFragment.arguments = bundle
        return exchangeRateFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.fragment_exchange_rate, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showExchangeRateData(commonExchangeRateModel: CommonExchangeRateModel) {
        val adapter = ExchangeRateAdapter(
            activity,
            commonExchangeRateModel.exchangeRateDataList
        )
        exchangeRateRecyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        exchangeRateRecyclerView!!.setAdapter(adapter)
    }

    override fun showMessageError(errorMessage: String?) {
        Toast.makeText(context, "Error: ${errorMessage}", Toast.LENGTH_LONG).show()
    }

    private fun injectDependency() {
        val exchangeRateComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        exchangeRateComponent.inject(this)
    }

    private fun initView() {
        presenter.loadExchangeRateData(arguments.getString(SELECTED_CURRENCY_ARGUMENT))
    }

    companion object {
        val TAG: String = "ExchangeRateFragment"
    }

}
