package com.finance.budgetok.contexts.sample.domain.datasource

import com.finance.budgetok.contexts.sample.domain.datasource.dao.AccountDao
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val accountDao: AccountDao
) {
    fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }

    fun getAccount(): Account? {
        return accountDao.getAccount()
    }

    fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }

    fun updateAccount(account: Account) {
        accountDao.updateAccount(account)
    }
}