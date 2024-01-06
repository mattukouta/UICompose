package com.kouta.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kouta.button.top.buttonTopScreen
import com.kouta.design.enums.DisplayMode
import com.kouta.design.navigation.Route
import com.kouta.design.theme.UIComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val isSystemInDarkTheme = isSystemInDarkTheme()
            var displayMode by remember { mutableStateOf(DisplayMode.findByValue(isSystemInDarkTheme)) }
            UIComposeTheme(
                darkTheme = displayMode == DisplayMode.DARK_MODE,
                dynamicColor = false
            ) {
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Route.MAIN_TOP.name
                    ) {
                        mainTopScreen(
                            navigate = {
                                navController.safeNavigate(it)
                            }
                        )

                        buttonTopScreen(
                            displayMode = displayMode,
                            displayModeChange = {
                                displayMode = it
                            },
                            popBackStack = {
                                navController.safePopBackStack()
                            },
                            navigate = {
                                navController.safeNavigate(it)
                            }
                        )
                    }
                }
            }
         }
    }

    private fun NavController.safePopBackStack() {
        if (previousBackStackEntry != null) {
            popBackStack()
        }
    }

    private fun NavController.safeNavigate(route: Route) {
        try {
            navigate(route.name)
        } catch (e: Exception) {
            Timber.d("ktakamat: 未設定のrouteにnavigateされました")
        }
    }
}