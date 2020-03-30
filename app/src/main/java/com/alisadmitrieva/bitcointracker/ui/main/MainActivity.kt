package com.alisadmitrieva.bitcointracker.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alisadmitrieva.bitcointracker.R
import com.alisadmitrieva.bitcointracker.di.component.DaggerActivityComponent
import com.alisadmitrieva.bitcointracker.di.module.ActivityModule
import com.alisadmitrieva.bitcointracker.ui.exchangerate.ExchangeRateFragment
import com.alisadmitrieva.bitcointracker.ui.majorcurrencies.MajorCurrenciesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
    }

    override fun showMajorCurrenciesFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(
                AnimType.SLIDE.getAnimPair().first,
                AnimType.SLIDE.getAnimPair().second
            )
            .replace(
                R.id.frame,
                MajorCurrenciesFragment().newInstance(),
                MajorCurrenciesFragment.TAG
            )
            .commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(ExchangeRateFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    enum class AnimType() {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            when (this) {
                SLIDE -> return Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> return Pair(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

}
