package com.finance.budgetok.infra.ui.view.popup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

inline fun <reified T : View> Fragment.inflate(
  layout: Int,
  container: ViewGroup? = null,
): T =
  LayoutInflater.from(requireContext())
      .inflate(layout, container) as T