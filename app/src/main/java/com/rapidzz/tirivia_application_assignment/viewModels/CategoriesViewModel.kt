package com.rapidzz.tirivia_application_assignment.viewModels


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel
import com.rapidzz.tirivia_application_assignment.models.dataSource.DataRepository
import kotlinx.coroutines.launch

class CategoriesViewModel(var context: Application) : BaseAndroidViewModel(context) {
    var dataRepository: DataRepository = DataRepository(context)
    var allCategoriesLiveData: LiveData<ArrayList<CategoryModel>>


    init {
        allCategoriesLiveData = dataRepository.allCategories as LiveData<ArrayList<CategoryModel>>
    }


    fun insertAllCategories(categories: ArrayList<CategoryModel>) =
        viewModelScope.launch { dataRepository.insertCategories(categories) }


}