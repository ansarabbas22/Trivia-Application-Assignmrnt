package com.rapidzz.tirivia_application_assignment.models.dataModels.utilityModels

import java.io.Serializable

data class ErrorResponse(var message: String, var code: Int) : Serializable