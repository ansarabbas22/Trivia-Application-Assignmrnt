package com.rapidzz.tirivia_application_assignment.utils.networkUtils

import com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels.ApiErrorResponse


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ApiErrorResponse? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}