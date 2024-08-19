package com.finance.budgetok.contexts.panel.domain.usecase

import com.finance.budgetok.contexts.sample.domain.datasource.UserDataSource
import com.finance.budgetok.contexts.sample.domain.usecase.GetValueUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PanelUseCase {
    suspend operator fun invoke(currentValue: Int): Int
}

class PanelUseCaseImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : PanelUseCase {

    override suspend fun invoke(currentValue: Int) = withContext(Dispatchers.IO) {
        5
    }

}