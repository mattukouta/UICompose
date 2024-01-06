package com.kouta.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kouta.design.compose.BaseTopScreen
import com.kouta.design.compose.UiComposeTopAppBar
import com.kouta.design.compose.UiPartsAt
import com.kouta.design.navigation.Route
import com.kouta.design.theme.UIComposeTheme

fun NavGraphBuilder.mainTopScreen(
    navigate: (Route) -> Unit
) {
    composable(Route.MAIN_TOP.name) {
        MainTopRoute(navigate)
    }
}

@Composable
fun MainTopRoute(
    navigate: (Route) -> Unit,
    viewModel: MainTopViewModel = hiltViewModel()
) {
    val dispatch = viewModel.dispatch

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is MainTopViewModel.ViewEvent.Navigate -> navigate(it.route)
            }
        }
    }


    BaseTopScreen(
        entries = Route.mainTopEntries(),
        onClickItem = { dispatch(MainTopViewModel.Action.OnClickItem(it)) }
    )
}