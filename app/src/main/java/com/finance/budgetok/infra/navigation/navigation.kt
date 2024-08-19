package com.finance.budgetok.infra.navigation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController

fun NavController.navigateSafe(
  actionId: Int,
  bundleOf: Bundle = Bundle.EMPTY,
) {
  if (currentDestination?.getAction(actionId) != null) {
    Log.d(
        this::class.java.simpleName,
        "debug: $currentDestination => $actionId with bundle $bundleOf",
    )
    navigate(actionId, bundleOf)
  } else {
    Log.d(
        this::class.java.simpleName,
        "🔴 can't navigate: $currentDestination => $actionId with bundle $bundleOf",
    )
  }
}