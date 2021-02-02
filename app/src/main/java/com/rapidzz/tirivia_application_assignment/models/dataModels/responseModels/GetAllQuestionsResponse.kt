package com.rapidzz.tirivia_application_assignment.models.dataModels.responseModels

import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.ResultModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels.BaseResponse
import java.io.Serializable

data class GetAllQuestionsResponse(
    val results: ArrayList<ResultModel>
): BaseResponse(), Serializable