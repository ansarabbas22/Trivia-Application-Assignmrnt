package com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels

import java.io.Serializable

open class BaseResponse(
    val response_code: Int = 0
) : Serializable