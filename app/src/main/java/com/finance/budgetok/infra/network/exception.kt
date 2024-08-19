package com.finance.budgetok.infra.network

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

fun Throwable.isUnitOutOfWork(): Boolean {
  val error = (this as? HttpException) ?: return false
  val errorString = error.response()
      ?.errorBody()
      ?.string()
      .orEmpty()
  return error.code() == 422 && errorString.contains("UnitOutOfWork", true)
}

fun Throwable.isInternetConnectionException(): Boolean {
  return when {
    this is UnknownHostException ||
        this is SSLHandshakeException ||
        this is SocketTimeoutException ||
        this is ConnectException -> true

    this is IOException -> {
      val connectionProblems = arrayOf(
          "network",
          "connect",
          "timeout",
          "UnknownHostException",
          "SSLHandshakeException",
      )
      val errorMessage = this.message ?: ""
      return connectionProblems.any { errorMessage.contains(it, ignoreCase = true) }
    }

    this is HttpException && (
        arrayOf(
            HTTP_STATUS_CODE_504_GATEWAY_TIMEOUT,
            HTTP_STATUS_CODE_522_CONNECTION_TIMEOUT,
            HTTP_STATUS_CODE_524_A_TIMEOUT_OCCURRED
        ).contains(code())
        ) -> true

    else -> false
  }
}

private const val HTTP_STATUS_CODE_504_GATEWAY_TIMEOUT = 504
private const val HTTP_STATUS_CODE_522_CONNECTION_TIMEOUT = 522
private const val HTTP_STATUS_CODE_524_A_TIMEOUT_OCCURRED = 524