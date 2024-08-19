package com.finance.budgetok.infra.ui.view

import android.content.Context
import android.view.View
import androidx.core.view.WindowCompat
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Custom BottomSheetDialog with container and coordinator's fitsSystemWindows property set to false
 * @see R.layout.design_bottom_sheet_dialog
 */
internal class FitsSystemWindowsBottomSheetDialog(
  context: Context,
  theme: Int,
) : BottomSheetDialog(context, theme) {
  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    window?.let {
      WindowCompat.setDecorFitsSystemWindows(it, false)
    }
    findViewById<View>(R.id.container)?.fitsSystemWindows = false
    findViewById<View>(R.id.coordinator)?.fitsSystemWindows = false
  }
}