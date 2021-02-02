package com.rapidzz.tirivia_application_assignment.view.adapters

import android.view.View
import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel
import com.rapidzz.tirivia_application_assignment.utils.application.loadImage
import kotlinx.android.synthetic.main.rv_category_list.view.*

class CategoryListAdapter(var items:ArrayList<CategoryModel>, var onClicker: OnItemClicker) :
    BaseAdapter(items,onClicker, R.layout.rv_category_list) {

    override fun View.bind(item: Any, position: Int) {
        val model = item as CategoryModel
        this.tvCategoryName.text = model.category_name
        this.ivCategoryImage.loadImage(model.category_image)
        this.tvPoints.text= "Earned pints : ${model.earned_points}"

    }

}