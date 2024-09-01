package com.finance.budgetok.infra.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.finance.budgetok.R

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val route: String,
    val selectedIconId: Int,
    val unselectedIcon: ImageVector,
    val titleTextId: Int,
) {
    PANEL(
        route = R.string.panel.toString(),
        selectedIconId = R.drawable.ic_home,
        unselectedIcon = Icons.Default.Check,
        titleTextId = R.string.panel,
    ),
    HISTORY(
        route = R.string.history.toString(),
        selectedIconId = R.drawable.ic_book,
        unselectedIcon = Icons.Default.Check,
        titleTextId = R.string.history,
    ),
    REPORT(
        route = R.string.report.toString(),
        selectedIconId = R.drawable.ic_movie,
        unselectedIcon = Icons.Default.Check,
        titleTextId = R.string.report,
    ),
    SETTINGS(
        route = R.string.settings.toString(),
        selectedIconId = R.drawable.ic_profile,
        unselectedIcon = Icons.Default.Check,
        titleTextId = R.string.settings,
    ),
}