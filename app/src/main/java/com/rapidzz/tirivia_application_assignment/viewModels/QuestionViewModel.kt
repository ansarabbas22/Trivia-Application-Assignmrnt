package com.rapidzz.tirivia_application_assignment.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.SolvedQuestionModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.responseModels.GetAllQuestionsResponse
import com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels.ApiErrorResponse
import com.rapidzz.tirivia_application_assignment.models.dataSource.DataRepository
import com.rapidzz.tirivia_application_assignment.models.dataSource.UserDataSource
import com.rapidzz.tirivia_application_assignment.utils.generalUtils.OneShotEvent
import kotlinx.coroutines.launch

class QuestionViewModel(var context: Application) : BaseAndroidViewModel(context) {
    var questionsListingsLiveData: MutableLiveData<OneShotEvent<GetAllQuestionsResponse>> =
        MutableLiveData()
    var dataRepository: DataRepository = DataRepository(context)

    fun getAllQuestions(
        amount: Int?,
        category: String?,
        difficulty: String?,
        type: String?
    ) {
        showProgressBar(true)
        dataRepository.getAllQuestions(amount, category, difficulty, type,
            object : UserDataSource.GetQuestionsCallback {
                override fun onSuccessResponse(response: GetAllQuestionsResponse) {
                    showProgressBar(false)
                    response?.let {
                        questionsListingsLiveData.value = OneShotEvent(it)
                    }

                }

                override fun onPayloadError(error: ApiErrorResponse) {
                    showProgressBar(false)
                    showSnackbarMessage(error.message)
                }
            })
    }

    fun updateCategory(category: CategoryModel) =
        viewModelScope.launch { dataRepository.updateCategory(category) }

    fun insertAllQuestions(solvedQuestionModel: List<SolvedQuestionModel>) =
        viewModelScope.launch { dataRepository.insertAllSolvedQuestion(solvedQuestionModel) }


}