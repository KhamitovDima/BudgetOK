package com.finance.budgetok.contexts.panel.features

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PanelFragmentArgs(
    val title: String,
) : Parcelable {
    companion object {
        const val ARGS_KEY = "args_key"
    }
}