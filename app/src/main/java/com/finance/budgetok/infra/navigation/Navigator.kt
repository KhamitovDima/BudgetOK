package com.finance.budgetok.infra.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.finance.budgetok.infra.activity.AppActivityProvider
import javax.inject.Inject

internal const val FRAGMENT_ADD_KEY = "fragment_add"

val FRAGMENT_ADD_FEATURE = FRAGMENT_ADD_KEY to true
typealias NavigateAction = NavController.(Activity) -> Unit

interface Navigator {

  operator fun invoke(
    id: Int,
    action: NavigateAction,
  )

  operator fun invoke(
    id: Int,
    activity: Activity? = null,
    action: NavigateAction,
  )
}

class JetpackNavigator @Inject constructor() : Navigator {

  override fun invoke(
    id: Int,
    action: NavigateAction,
  ) {
    val activity = AppActivityProvider.getActivity() ?: return
    activity.findNavController(id)
        .action(activity)
  }

  override fun invoke(
    id: Int,
    activity: Activity?,
    action: NavigateAction,
  ) {
    val resolvedActivity = activity ?: AppActivityProvider.getActivity() ?: return
    resolvedActivity.findNavController(id)
        .action(resolvedActivity)
  }
}