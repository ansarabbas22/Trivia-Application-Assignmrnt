package com.rapidzz.tirivia_application_assignment.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels.BaseResponse
import com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels.ErrorResponse
import com.rapidzz.tirivia_application_assignment.utils.generalUtils.OneShotEvent

open class BaseAndroidViewModel(context: Application) : AndroidViewModel(context) {

    val snackbarMessage = MutableLiveData<OneShotEvent<String>>()
    val snackbarCodeMessage = MutableLiveData<OneShotEvent<BaseResponse>>()
    val progressBar = MutableLiveData<OneShotEvent<Boolean>>()
    val validationResponse = MutableLiveData<OneShotEvent<ErrorResponse>>()


    protected fun getContext(): Context {
        return getApplication<Application>().applicationContext
    }

    protected fun getString(resId: Int): String {
        return getContext().getString(resId)
    }

    protected fun handleErrorType(errorType: Int, errorMessage: String) {
        val error = ErrorResponse(
            errorMessage,
            errorType
        )
        this.validationResponse.value = OneShotEvent(error)
    }

    protected fun showSnackbarMessage(message: String) {
        snackbarMessage.value = OneShotEvent(message)
    }

    protected fun showSnackbarCodeMessage(baseResponse: BaseResponse) {
        snackbarCodeMessage.value = OneShotEvent(baseResponse)
    }



    protected fun showProgressBar(show: Boolean) {
        progressBar.value = OneShotEvent(show)
    }
}