package com.kouta.compose

import androidx.lifecycle.ViewModel
import com.kouta.button.top.UiState
import com.kouta.design.extension.launch
import com.kouta.design.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainTopViewModel @Inject constructor(

) : ViewModel() {
    sealed class Action {
        data class OnClickItem(val route: Route) : Action()
    }

    sealed class ViewEvent {
        data class Navigate(val route: Route) : ViewEvent()
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
            is Action.OnClickItem -> navigate(action.route)
        }
    }

    private suspend fun navigate(route: Route) {
        _viewEvent.send(ViewEvent.Navigate(route))
    }
}