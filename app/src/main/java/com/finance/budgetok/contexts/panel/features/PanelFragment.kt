package com.finance.budgetok.contexts.panel.features

import android.os.Bundle
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.finance.budgetok.R
import com.finance.budgetok.contexts.panel.features.compose.FinanceScreen
import com.finance.budgetok.contexts.panel.features.di.PanelComponent
import com.finance.budgetok.contexts.sample.features.compose.SampleScreen
import com.finance.budgetok.contexts.sample.features.di.SampleComponent
import com.finance.budgetok.contexts.panel.features.mvi.PanelAction
import com.finance.budgetok.contexts.panel.features.mvi.PanelEvent
import com.finance.budgetok.contexts.panel.features.mvi.PanelState
import com.finance.budgetok.databinding.SampleFragmentBinding
import com.finance.budgetok.infra.di.componentdependencies.api.findDependencies
import com.finance.budgetok.infra.mvi.ActionConsumer
import com.finance.budgetok.infra.mvi.EventsOwner
import com.finance.budgetok.infra.mvi.StateOwner
import com.finance.budgetok.infra.ui.view.CommonVMFragment
import com.finance.budgetok.infra.ui.view.viewbinding.viewBinding
import javax.inject.Inject

class PanelFragment : CommonVMFragment(R.layout.sample_fragment) {

    private val viewBinding by viewBinding<SampleFragmentBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sampleArgs: PanelFragmentArgs by lazy(LazyThreadSafetyMode.NONE) {
        requireNotNull(requireArguments().getParcelable(PanelFragmentArgs.ARGS_KEY))
    }

    private val viewModel: PanelViewModel by viewModels { viewModelFactory }
    private val actionConsumer: ActionConsumer<PanelAction> by lazy(
        LazyThreadSafetyMode.NONE
    ) { viewModel.actionConsumer }
    private val stateOwner: StateOwner<PanelState> by lazy(
        LazyThreadSafetyMode.NONE
    ) { viewModel.stateOwner }
    private val eventsOwner: EventsOwner<PanelEvent> by lazy(
        LazyThreadSafetyMode.NONE
    ) { viewModel.eventsOwner }

    override fun onCreate(savedInstanceState: Bundle?) {
        PanelComponent.factory.create(
            dependencies = findDependencies()
        )
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreate() {
        super.onViewCreate()
        viewBinding.composeView.setContent {

            Surface {
                val state by stateOwner.state.collectAsStateWithLifecycle()

                FinanceScreen(
                    state = state,
                    onButtonClick = (::buttonClicked),
                    modifier = Modifier.testTag(SAMPLE_FRAGMENT)
                )
            }
        }

    }

    private fun buttonClicked() {
        actionConsumer.consumeAction(PanelAction.ButtonClicked)
    }

    companion object {
        const val SAMPLE_FRAGMENT = "SampleFragment"
    }
}