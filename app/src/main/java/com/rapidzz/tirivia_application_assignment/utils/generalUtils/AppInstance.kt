package com.rapidzz.tirivia_application_assignment.utils.generalUtils

import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel

object AppInstance {
    fun getCategoryList(): ArrayList<CategoryModel> {

        var categoryList: ArrayList<CategoryModel> = ArrayList()
        categoryList.clear()
        categoryList.add(CategoryModel(13, "Entertainment: Musicals & Theatres", R.drawable.ic_musical, 0))
        categoryList.add(CategoryModel(22, "Geography", R.drawable.ic_geography, 0))
        categoryList.add(CategoryModel(15, "Entertainment: Video Games", R.drawable.ic_video_games, 0))
        categoryList.add(CategoryModel(19, "Science: Mathematics", R.drawable.ic_mathematics, 0))
        categoryList.add(CategoryModel(14, "Entertainment: Television", R.drawable.ic_television, 0))
        categoryList.add(CategoryModel(18, "Science Computers", R.drawable.ic_computer_science, 0))
        categoryList.add(CategoryModel(26, "Celebrities", R.drawable.ic_celebrity, 0))
        categoryList.add(CategoryModel(27, "Animals", R.drawable.ic_animals, 0))
        categoryList.add(CategoryModel(28, "Vehicles", R.drawable.ic_vehicles, 0))
        categoryList.add(CategoryModel(23, "History", R.drawable.ic_history, 0))
        return categoryList
    }
}