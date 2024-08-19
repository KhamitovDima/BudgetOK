package com.finance.budgetok.infra.ui.view.popup

interface PopupView {
  fun show(text: String)
}

interface AlertView : PopupView
interface ToastView : PopupView