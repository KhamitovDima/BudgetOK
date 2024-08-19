package com.finance.budgetok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.finance.budgetok.contexts.sample.domain.datasource.db.AppDatabase
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistryProvider
import com.finance.budgetok.infra.di.componentdependencies.impl.delegatingComponentDependenciesRegistry

class MainActivity : FragmentActivity(), ComponentDependenciesRegistryProvider {

    override val componentDependenciesRegistry = delegatingComponentDependenciesRegistry()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        initRoom()
        setContentView(R.layout.activity_main)
    }

    private fun initRoom() {
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "finance-db"
        ).build()
    }
}
