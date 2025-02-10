
package com.arteaga.marlon.domain.fake

import com.arteaga.marlon.domain.models.ErrorGeneric

object ErrorGenericTestData {
    const val code: Int = 404
    const val description: String = "Not Found"

    fun create() = ErrorGeneric(code, description)
}
