package com.rapidzz.tirivia_application_assignment.models.dataSource.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.SolvedQuestionModel

@Dao
interface AnswersDao : BaseDao<SolvedQuestionModel> {
    @Query("SELECT * from solved_question")
    fun getAllSolvedQuestions(): LiveData<List<SolvedQuestionModel>>
}