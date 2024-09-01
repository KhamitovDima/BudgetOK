package com.finance.budgetok

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.SparseArray
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.finance.budgetok.contexts.sample.domain.datasource.db.AppDatabase
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistryProvider
import com.finance.budgetok.infra.di.componentdependencies.impl.delegatingComponentDependenciesRegistry
import com.finance.budgetok.infra.navigation.BottomNavigationBar
import com.finance.budgetok.infra.navigation.NavigationNavHost
import com.finance.budgetok.ui.theme.BudgetOKTheme

class MainActivity : FragmentActivity(), ComponentDependenciesRegistryProvider {

    override val componentDependenciesRegistry = delegatingComponentDependenciesRegistry()

    private var savedStateSparseArray = SparseArray<Fragment.SavedState>()
    private var currentSelectItemId = 0

    companion object {
        const val SAVED_STATE_CONTAINER_KEY = "ContainerKey"
        const val SAVED_STATE_CURRENT_TAB_KEY = "CurrentTabKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            savedStateSparseArray = savedInstanceState.getSparseParcelableArray(
                SAVED_STATE_CONTAINER_KEY
            )
                ?: savedStateSparseArray
            currentSelectItemId = savedInstanceState.getInt(SAVED_STATE_CURRENT_TAB_KEY)
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        initRoom()

        setContent {
            BudgetOKTheme {
                MainScreen()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSparseParcelableArray(SAVED_STATE_CONTAINER_KEY, savedStateSparseArray)
        outState.putInt(SAVED_STATE_CURRENT_TAB_KEY, currentSelectItemId)
    }

    private fun initRoom() {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "finance-db"
        ).build()
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NavigationNavHost(navController, ::getCommitFunction)
            }
        }
    }

    private fun getCommitFunction(
        fragment: Fragment,
        tag: String
    ): FragmentTransaction.(containerId: Int) -> Unit =
        {
            saveAndRetrieveFragment(supportFragmentManager, it, fragment)
            replace(it, fragment, tag)
        }

    private fun saveAndRetrieveFragment(
        supportFragmentManager: FragmentManager,
        tabId: Int,
        fragment: Fragment
    ) {
        val currentFragment = supportFragmentManager.findFragmentById(currentSelectItemId)
        if (currentFragment != null) {
            savedStateSparseArray.put(
                currentSelectItemId,
                supportFragmentManager.saveFragmentInstanceState(currentFragment)
            )
        }
        currentSelectItemId = tabId
        fragment.setInitialSavedState(savedStateSparseArray[currentSelectItemId])
    }
}
