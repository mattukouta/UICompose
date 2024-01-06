package com.kouta.button.top

import androidx.lifecycle.ViewModel
import com.kouta.design.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ButtonTopViewModel @Inject constructor(

) : ViewModel() {
    sealed class Action {
        data object OnClickBack : Action()
    }

    sealed class ViewEvent {
        data object PopBackStack : ViewEvent()
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
        }
    }

    private suspend fun popBackStack() = _viewEvent.send(ViewEvent.PopBackStack)
}