package com.rapidzz.tirivia_application_assignment.models.dataSource.remote

import com.rapidzz.tirivia_application_assignment.models.dataModels.responseModels.GetAllQuestionsResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("api.php")
    fun getAllQuestions(
        @Query("amount") amount: Int?,
        @Query("category") category: String?,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): Call<GetAllQuestionsResponse>
}