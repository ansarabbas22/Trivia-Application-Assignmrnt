package com.rapidzz.tirivia_application_assignment.models.dataSource.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel


@Dao
interface CategoriesDao : BaseDao<CategoryModel> {
    @Query("SELECT * from category_model")
    fun getAllCategories(): LiveData<List<CategoryModel>>

}