package com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "solved_question")
class SolvedQuestionModel(

    @PrimaryKey(autoGenerate = true)
    val question_id: Int,
    val category_id: String,
    val correct_answer: String,
    val difficulty: String,
    val question: String,
    val type: String,
    var selectedAnswer: String
) :Serializable