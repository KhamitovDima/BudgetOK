package com.finance.budgetok.infra.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentTransaction
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.finance.budgetok.contexts.panel.features.PanelFragment
import com.finance.budgetok.infra.ui.compose.navigation.FragmentContainer

@Composable
fun NavigationNavHost(
    navController: NavHostController,
    getCommitFunction: (
        fragment: Fragment,
        tag: String
    ) -> (FragmentTransaction.(containerId: Int) -> Unit)
) {
    NavHost(navController, startDestination = TopLevelDestination.PANEL.route) {
        enumValues<TopLevelDestination>().forEach { item ->
            composable(item.route) {
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    commit = getCommitFunction(
                        PanelFragment.newInstance(),
                        item.route
                    )
                )
            }
        }
    }
}
