package com.finance.budgetok.contexts.sample.domain.usecase

import com.finance.budgetok.contexts.sample.domain.datasource.UserDataSource
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetValueUseCase {
    suspend operator fun invoke(currentValue: Int): Int
}

class GetValueUseCaseImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : GetValueUseCase {

    private val newAccount = Account(
        id = "uuid",
        name = "Savings",
        type = 0,
        summa = 1000.0,
        id_val = 5,
        id_color = 1,
        id_icon = 1,
        por_nom = 1,
        vis = 1,
        uchet = 1,
        updated = "2024-08-05",
        isDeleted = 0
    )

    override suspend fun invoke(currentValue: Int) = withContext(Dispatchers.IO) {
        var id = currentValue
        val account = userDataSource.getAccount()
        if (account == null) {
            userDataSource.insertAccount(newAccount)
        } else {
            id = account.id_val
            if (id <= 30) {
                val result = if (id % 2 == 0) {
                    id + 1
                } else {
                    id * 2
                }
                userDataSource.updateAccount(account.copy(id_val = result))
            } else {
                userDataSource.deleteAccount(account)
            }
        }
        id
    }

}