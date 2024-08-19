package com.finance.budgetok.contexts.sample.features

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SampleFragmentArgs(
    val title: String,
) : Parcelable {
    companion object {
        const val ARGS_KEY = "args_key"
    }
}