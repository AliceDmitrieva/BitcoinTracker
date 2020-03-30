package com.alisadmitrieva.bitcointracker.ui.majorcurrencies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alisadmitrieva.bitcointracker.R
import com.alisadmitrieva.bitcointracker.di.component.DaggerFragmentComponent
import com.alisadmitrieva.bitcointracker.di.module.FragmentModule
import com.alisadmitrieva.bitcointracker.ui.exchangerate.ExchangeRateFragment
import com.alisadmitrieva.bitcointracker.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_major_currencies.*
import javax.inject.Inject

class MajorCurrenciesFragment : Fragment(), MajorCurrenciesContract.View,
    MajorCurrenciesAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: MajorCurrenciesContract.Presenter
    private lateinit var rootView: View

    fun newInstance(): MajorCurrenciesFragment {
        return MajorCurrenciesFragment()
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
        rootView = inflater!!.inflate(R.layout.fragment_major_currencies, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    override fun showMajorCurrenciesData() {
        val adapter = MajorCurrenciesAdapter(
            activity,
            resources.getStringArray(R.array.majorCurrencies),
            this
        )
        majorCurrenciesRecyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        majorCurrenciesRecyclerView!!.setAdapter(adapter)
    }

    override fun itemDetail(selectedCurrencyID: String) {
        presenter.selectCurrency(selectedCurrencyID)
    }

    private fun injectDependency() {
        val majorCurrenciesComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        majorCurrenciesComponent.inject(this)
    }

    private fun initView() {
        presenter.loadMajorCurrenciesData()
    }

    override fun showExchangeRateFragment(selectedCurrencyID: String) {
        activity.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(
                MainActivity.AnimType.FADE.getAnimPair().first,
                MainActivity.AnimType.FADE.getAnimPair().second
            )
            .replace(
                R.id.frame,
                ExchangeRateFragment().newInstance(selectedCurrencyID),
                ExchangeRateFragment.TAG
            )
            .commit()
    }

    companion object {
        val TAG: String = "MajorCurrenciesFragment"
    }

}
