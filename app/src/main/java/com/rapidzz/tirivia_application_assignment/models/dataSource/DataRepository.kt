package com.rapidzz.tirivia_application_assignment.models.dataSource

import android.content.Context
import androidx.lifecycle.LiveData
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.SolvedQuestionModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.responseModels.GetAllQuestionsResponse
import com.rapidzz.tirivia_application_assignment.models.dataSource.remote.ApiService
import com.rapidzz.tirivia_application_assignment.models.dataSource.remote.RetrofitClientInstance
import com.rapidzz.tirivia_application_assignment.models.dataSource.room.CategoriesDao
import com.rapidzz.tirivia_application_assignment.models.dataSource.room.AnswersDao
import com.rapidzz.tirivia_application_assignment.models.dataSource.room.TriviaDatabase
import com.rapidzz.tirivia_application_assignment.utils.application.isInternetConnected
import com.rapidzz.tirivia_application_assignment.utils.generalUtils.ErrorUtils
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call

class DataRepository(ctx: Context) {
    var context: Context = ctx
    private val categoriesDao: CategoriesDao = TriviaDatabase.getDatabase(ctx).categoriesDao()
    private val answersDao: AnswersDao = TriviaDatabase.getDatabase(ctx).questionAnswersDao()


    fun getApiService(): ApiService {
        return RetrofitClientInstance.getInstance(context)!!.getService()
    }

    fun getAllQuestions(
        amount: Int?,
        category: String?,
        difficulty: String?,
        type: String?,
        callback: UserDataSource.GetQuestionsCallback
    ) {
        if (checkInternetConnection(callback)) return
        getApiService().getAllQuestions(amount, category, difficulty, type)
            .enqueue(object : Callback<GetAllQuestionsResponse> {
                override fun onResponse(
                    call: Call<GetAllQuestionsResponse>,
                    response: Response<GetAllQuestionsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccessResponse(it)
                        }
                    } else {
                        callback.onPayloadError(
                            ErrorUtils.parseError(
                                response.errorBody()!!.string()
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<GetAllQuestionsResponse>, t: Throwable) {
                    callback.onPayloadError(ErrorUtils.parseError(t))
                    t.printStackTrace()
                }
            })
    }


    val allCategories: LiveData<List<CategoryModel>> = categoriesDao.getAllCategories()


    suspend fun insertCategories(categories: List<CategoryModel>) {
        categoriesDao.insert(categories)
    }

    suspend fun updateCategory(category: CategoryModel) {
        categoriesDao.update(category)
    }

    suspend fun insertAllSolvedQuestion(solvedQuestionModels: List<SolvedQuestionModel>) {
        answersDao.insert(solvedQuestionModels)
    }


    private fun checkInternetConnection(callback: BaseDataSource): Boolean {
        if (!isInternetConnected(context)) {
            callback.onPayloadError(
                ErrorUtils.parseError(
                    "{\"message\":\"Please check internet connection and try again\",\"code\":\"0\",\"name\":\"error\"}"
                )
            )
            return true
        }
        return false
    }
}