package com.kouta.button.button

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.kouta.design.enums.DisplayMode
import com.kouta.design.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ButtonViewModel @Inject constructor() : ViewModel() {
    sealed class Action {
        data object OnClickBack : Action()
        data class OnClickEnabled(val enabled: Boolean) : Action()
        data class OnClickShape(val shape: UiState.SelectableShape) : Action()
        data class OnClickDisplayMode(val displayMode: DisplayMode) : Action()
        data class OnDefaultElevationChanged(val value: TextFieldValue) : Action()
        data class OnPressedElevationChanged(val value: TextFieldValue) : Action()
        data class OnFocusedElevationChanged(val value: TextFieldValue) : Action()
        data class OnHoveredElevationChanged(val value: TextFieldValue) : Action()
        data class OnDisabledElevationChanged(val value: TextFieldValue) : Action()
    }

    sealed class ViewEvent {
        data object PopBackStack : ViewEvent()
        data class DisplayModeChange(val displayMode: DisplayMode) : ViewEvent()
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState

    private val _viewEvent = Channel<ViewEvent>()
    val viewEvent = _viewEvent.receiveAsFlow()

    val dispatch: (Action) -> Unit = {
        launch {
            handleAction(it)
        }
    }

    private suspend fun handleAction(action: Action) {
        when (action) {
            Action.OnClickBack -> popBackStack()
            is Action.OnClickEnabled -> enabledChange(action.enabled)
            is Action.OnClickShape -> shapeChange(action.shape)
            is Action.OnClickDisplayMode -> displayModeChange(action.displayMode)
            is Action.OnDefaultElevationChanged -> onDefaultElevationChanged(action.value)
            is Action.OnPressedElevationChanged -> onPressedElevationChanged(action.value)
            is Action.OnFocusedElevationChanged -> onFocusedElevationChanged(action.value)
            is Action.OnHoveredElevationChanged -> onHoveredElevationChanged(action.value)
            is Action.OnDisabledElevationChanged -> onDisabledElevationChanged(action.value)
        }
    }

    private suspend fun popBackStack() = _viewEvent.send(ViewEvent.PopBackStack)
    private fun enabledChange(enabled: Boolean)  {
        _uiState.update {
            it.copy(enabled = enabled)
        }
    }

    private fun shapeChange(shape: UiState.SelectableShape) {
        _uiState.update {
            it.copy(shape = shape)
        }
    }

    private suspend fun displayModeChange(displayMode: DisplayMode) {
        _viewEvent.send(ViewEvent.DisplayModeChange(displayMode))
    }

    private fun onDefaultElevationChanged(value: TextFieldValue) {
        _uiState.update {
            it.copy(elevation = it.elevation.copy(defaultElevation = value))
        }
    }

    private fun onPressedElevationChanged(value: TextFieldValue) {
        _uiState.update {
            it.copy(elevation = it.elevation.copy(pressedElevation = value))
        }
    }

    private fun onFocusedElevationChanged(value: TextFieldValue) {
        _uiState.update {
            it.copy(elevation = it.elevation.copy(focusedElevation = value))
        }
    }

    private fun onHoveredElevationChanged(value: TextFieldValue) {
        _uiState.update {
            it.copy(elevation = it.elevation.copy(hoveredElevation = value))
        }
    }

    private fun onDisabledElevationChanged(value: TextFieldValue) {
        _uiState.update {
            it.copy(elevation = it.elevation.copy(disabledElevation = value))
        }
    }
}