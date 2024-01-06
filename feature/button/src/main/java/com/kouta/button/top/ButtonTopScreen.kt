package com.kouta.button.top

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kouta.button.button.ButtonRoute
import com.kouta.button.top.ButtonTopViewModel.Action
import com.kouta.button.top.ButtonTopViewModel.ViewEvent
import com.kouta.design.compose.BaseTopScreen
import com.kouta.design.enums.DisplayMode
import com.kouta.design.navigation.Route

fun NavGraphBuilder.buttonTopScreen(
    displayMode: DisplayMode,
    displayModeChange: (DisplayMode) -> Unit,
    popBackStack: () -> Unit,
    navigate: (Route) -> Unit
) {
    composable(Route.BUTTON_TOP.name) {
        ButtonTopRoute(
            popBackStack = popBackStack,
            onClickItem = navigate
        )
    }

    composable(Route.BUTTON.name) {
        ButtonRoute(
            displayMode = displayMode,
            displayModeChange = displayModeChange,
            popBackStack = popBackStack
        )
    }
}

@Composable
fun ButtonTopRoute(
    viewModel: ButtonTopViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    onClickItem: (Route) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val dispatch = viewModel.dispatch

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                ViewEvent.PopBackStack -> {
                    popBackStack()
                }
            }
        }
    }

    BaseTopScreen(
        entries = Route.buttonEntries(),
        onClickBack = {
            dispatch(Action.OnClickBack)
        },
        onClickItem = {
            onClickItem(it)
        }
    )
}