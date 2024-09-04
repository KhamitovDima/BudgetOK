package com.finance.budgetok.contexts.panel.features.categoriesattraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.finance.budgetok.R
import com.finance.budgetok.contexts.panel.domain.DecimalFormatter
import com.finance.budgetok.contexts.panel.features.categoriesattraction.compose.DecimalInputField
import com.finance.budgetok.contexts.panel.features.categoriesattraction.di.CategoriesAttractionComponent
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionAction
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionEvent
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionState
import com.finance.budgetok.infra.di.componentdependencies.api.findDependencies
import com.finance.budgetok.infra.di.componentdependencies.impl.delegatingComponentDependenciesRegistry
import com.finance.budgetok.infra.mvi.ActionConsumer
import com.finance.budgetok.infra.mvi.EventsOwner
import com.finance.budgetok.infra.mvi.StateOwner
import com.finance.budgetok.infra.ui.compose.components.VSpacer
import com.finance.budgetok.infra.ui.view.CommonVMBottomSheetFragment
import javax.inject.Inject

class CategoriesAttractionBottomSheetFragment : CommonVMBottomSheetFragment() {

    override fun getLayoutId() = R.layout.fragment_compose_view

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CategoriesAttractionViewModel by viewModels { viewModelFactory }
    private val actionConsumer: ActionConsumer<CategoriesAttractionAction> by lazy(
        LazyThreadSafetyMode.NONE
    ) { viewModel.actionConsumer }
    private val stateOwner: StateOwner<CategoriesAttractionState> by lazy(
        LazyThreadSafetyMode.NONE
    ) { viewModel.stateOwner }
    private val eventsOwner: EventsOwner<CategoriesAttractionEvent> by lazy(
        LazyThreadSafetyMode.NONE
    ) { viewModel.eventsOwner }

    override val componentDependenciesRegistry =
        delegatingComponentDependenciesRegistry()

    override fun onCreate(savedInstanceState: Bundle?) {
        CategoriesAttractionComponent.factory.create(
            dependencies = findDependencies()
        )
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): android.view.View {
        val view = super.onCreateView(inflater, container, savedInstanceState) as ComposeView
        view.setContent {
            val decimalFormatter = DecimalFormatter()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                DecimalInputField(decimalFormatter = decimalFormatter)
            }

        }

        return view
    }
}

    /*val decimalFormatter = DecimalFormatter()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                DecimalInputField(decimalFormatter = decimalFormatter)
            }*/