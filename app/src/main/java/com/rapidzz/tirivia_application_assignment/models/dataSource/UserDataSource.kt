package com.rapidzz.tirivia_application_assignment.models.dataSource

import com.rapidzz.tirivia_application_assignment.models.dataModels.responseModels.GetAllQuestionsResponse


interface UserDataSource {

    interface GetQuestionsCallback : BaseDataSource {
        fun onSuccessResponse(response: GetAllQuestionsResponse)
    }
}