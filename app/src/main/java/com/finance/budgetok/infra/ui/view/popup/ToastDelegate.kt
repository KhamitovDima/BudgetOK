package com.finance.budgetok.infra.ui.view.popup

import android.widget.Toast
import com.finance.budgetok.infra.activity.AppActivityProvider


class ToastDelegate : ToastView {

  override fun show(text: String) {
    val activity = AppActivityProvider.getActivity() ?: return
    activity.runOnUiThread {
      Toast.makeText(activity, text, Toast.LENGTH_SHORT)
          .show()
    }
  }
}