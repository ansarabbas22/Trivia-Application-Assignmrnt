package com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "category_model")
data class CategoryModel(
    @PrimaryKey
    var category_id: Int,
    var category_name: String,
    var category_image: Int,
    var earned_points: Int
) : Serializable