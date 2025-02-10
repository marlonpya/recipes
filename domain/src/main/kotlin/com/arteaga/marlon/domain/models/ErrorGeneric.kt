
package com.arteaga.marlon.domain.models

class ErrorGeneric(
    val code: Int,
    val description: String,
) : Exception(description)
