package com.rapidzz.tirivia_application_assignment.models.dataSource

import com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels.ApiErrorResponse


interface BaseDataSource {
    fun onPayloadError(error: ApiErrorResponse)
}
