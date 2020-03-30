package com.alisadmitrieva.bitcointracker.ui.base

class BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
    }

    interface View

}
