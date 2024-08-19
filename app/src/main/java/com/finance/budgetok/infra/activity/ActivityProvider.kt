package com.finance.budgetok.infra.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

interface ActivityProvider {
  fun getActivity(): Activity?
}

object AppActivityProvider : ActivityProvider {
  private var activityRef: WeakReference<Activity>? = null

  fun initialize(app: Application): ActivityProvider {
    app.registerActivityLifecycleCallbacks(object : ActivityLifecycleAdapter() {

      override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?,
      ) {
        super.onActivityCreated(activity, savedInstanceState)
        activityRef = WeakReference(activity)
      }

      override fun onActivityStarted(activity: Activity) {
        super.onActivityStarted(activity)
        activityRef = WeakReference(activity)
      }

      override fun onActivityPreStopped(activity: Activity) {
        super.onActivityPreStopped(activity)
        if (activityRef?.get() == activity) {
          activityRef = null
        }
      }

      override fun onActivityPreDestroyed(activity: Activity) {
        super.onActivityPreDestroyed(activity)
        if (activityRef?.get() == activity) {
          activityRef = null
        }
      }
    })
    return this
  }

  override fun getActivity(): Activity? = activityRef?.get()

}