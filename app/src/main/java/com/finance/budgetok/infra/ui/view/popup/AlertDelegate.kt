package com.finance.budgetok.infra.ui.view.popup

import android.app.AlertDialog
import com.finance.budgetok.R
import com.finance.budgetok.infra.activity.AppActivityProvider

class AlertDelegate : AlertView {

  override fun show(text: String) {
    val activity = AppActivityProvider.getActivity() ?: return
    activity.runOnUiThread {
      AlertDialog.Builder(activity)
          .setTitle(R.string.general_error_caption)
          .setMessage(text)
          .setPositiveButton(android.R.string.ok) { _, _ -> }
          .create()
          .show()
    }
  }
}