package com.kouta.button.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kouta.button.button.ButtonViewModel.Action
import com.kouta.button.button.ButtonViewModel.ViewEvent
import com.kouta.design.compose.ComponentDetail
import com.kouta.design.compose.NumberInputAt
import com.kouta.design.compose.SingleSelectAt
import com.kouta.design.enums.DisplayMode
import com.kouta.design.enums.Enable
import com.kouta.design.navigation.Route
import com.kouta.design.vo.InputSelection
import com.kouta.design.vo.SingleSelectSelection

@Composable
fun ButtonRoute(
    viewModel: ButtonViewModel = hiltViewModel(),
    displayMode: DisplayMode,
    displayModeChange: (DisplayMode) -> Unit,
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val dispatch = viewModel.dispatch

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                ViewEvent.PopBackStack -> popBackStack()
                is ViewEvent.DisplayModeChange -> displayModeChange(it.displayMode)
            }
        }
    }

    ButtonScreen(
        uiState = uiState,
        displayMode = displayMode,
        onClickBack = {
            dispatch(Action.OnClickBack)
        },
        onClickEnabled = { dispatch(Action.OnClickEnabled(it)) },
        onClickShape = { dispatch(Action.OnClickShape(it)) },
        onClickDisplayMode = { dispatch(Action.OnClickDisplayMode(it)) },
        onDefaultElevationChanged = { dispatch(Action.OnDefaultElevationChanged(it)) },
        onPressedElevationChanged = { dispatch(Action.OnPressedElevationChanged(it)) },
        onFocusedElevationChanged = { dispatch(Action.OnFocusedElevationChanged(it)) },
        onHoveredElevationChanged = { dispatch(Action.OnHoveredElevationChanged(it)) },
        onDisabledElevationChanged = { dispatch(Action.OnDisabledElevationChanged(it)) },
    )
}

@Composable
fun ButtonScreen(
    uiState: UiState,
    displayMode: DisplayMode,
    onClickBack: () -> Unit,
    onClickEnabled: (Boolean) -> Unit,
    onClickShape: (UiState.SelectableShape) -> Unit,
    onClickDisplayMode: (DisplayMode) -> Unit,
    onDefaultElevationChanged: (TextFieldValue) -> Unit,
    onPressedElevationChanged: (TextFieldValue) -> Unit,
    onFocusedElevationChanged: (TextFieldValue) -> Unit,
    onHoveredElevationChanged: (TextFieldValue) -> Unit,
    onDisabledElevationChanged: (TextFieldValue) -> Unit,
) {
    ComponentDetail(
        title = Route.BUTTON.title,
        displayMode = displayMode,
        onClickBack = onClickBack,
        onClickDisplayMode = onClickDisplayMode,
        content = {
            Button(
                onClick = {},
                enabled = uiState.enabled,
                shape = uiState.shape.shape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = uiState.buttonColors.containerColor
                        ?: MaterialTheme.colorScheme.primary,
                    contentColor = uiState.buttonColors.contentColor
                        ?: MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = (uiState.buttonColors.disabledContainerColor
                        ?: MaterialTheme.colorScheme.onSurface).copy(alpha = 0.12f),
                    disabledContentColor = (uiState.buttonColors.disabledContentColor
                        ?: MaterialTheme.colorScheme.onSurface).copy(alpha = 0.38f)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = (uiState.elevation.defaultElevation.text.toIntOrNull()
                        ?: 0).dp,
                    pressedElevation = (uiState.elevation.pressedElevation.text.toIntOrNull()
                        ?: 0).dp,
                    focusedElevation = (uiState.elevation.focusedElevation.text.toIntOrNull()
                        ?: 0).dp,
                    hoveredElevation = (uiState.elevation.hoveredElevation.text.toIntOrNull()
                        ?: 0).dp,
                    disabledElevation = (uiState.elevation.disabledElevation.text.toIntOrNull()
                        ?: 0).dp
                ),
                border = uiState.border,
                contentPadding = uiState.contentPadding,
                interactionSource = uiState.interactionSource,
            ) {
                Text(text = "button")
            }
        },
        sheetContent = {
            SingleSelectAt(
                label = "enabled",
                selection = SingleSelectSelection(
                    candidates = Enable.entries,
                    selected = Enable.findByValue(uiState.enabled)
                ),
                onClickItem = {
                    onClickEnabled(it.value)
                }
            )

            SingleSelectAt(
                label = "shape",
                selection = SingleSelectSelection(
                    candidates = UiState.SelectableShape.entries,
                    selected = uiState.shape
                ),
                onClickItem = {
                    onClickShape(it)
                }
            )

            NumberInputAt(
                label = "elevation",
                selections = listOf(
                    InputSelection(
                        label = "defaultElevation",
                        value = uiState.elevation.defaultElevation,
                        onValueChange = onDefaultElevationChanged
                    ),
                    InputSelection(
                        label = "pressedElevation",
                        value = uiState.elevation.pressedElevation,
                        onValueChange = onPressedElevationChanged
                    ),
                    InputSelection(
                        label = "focusedElevation",
                        value = uiState.elevation.focusedElevation,
                        onValueChange = onFocusedElevationChanged
                    ),
                    InputSelection(
                        label = "hoveredElevation",
                        value = uiState.elevation.hoveredElevation,
                        onValueChange = onHoveredElevationChanged
                    ),
                    InputSelection(
                        label = "disabledElevation",
                        value = uiState.elevation.disabledElevation,
                        onValueChange = onDisabledElevationChanged
                    ),
                )
            )
        }
    )
}
