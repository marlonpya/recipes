
package com.arteaga.marlon.recipes.ui.state

data class MutableUiStateHolder(override var currentState: UiState = UiState.INIT) : UiStateHolder
