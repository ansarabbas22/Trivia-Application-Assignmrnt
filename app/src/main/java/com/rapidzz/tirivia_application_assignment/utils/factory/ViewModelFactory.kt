package com.rapidzz.tirivia_application_assignment.utils.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rapidzz.tirivia_application_assignment.models.dataSource.DataRepository
import com.rapidzz.tirivia_application_assignment.viewModels.CategoriesViewModel
import com.rapidzz.tirivia_application_assignment.viewModels.QuestionViewModel


class ViewModelFactory private constructor(
    private val application: Application,
    private val dataRepository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CategoriesViewModel::class.java) ->
                    CategoriesViewModel(application)
                isAssignableFrom(QuestionViewModel::class.java) ->
                    QuestionViewModel(application)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            application,
                            DataRepository(application.applicationContext)
                        )
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

