package com.muen.gameflootit.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.muen.gameflootit.ui.FloodItScreen.GAME
import com.muen.gameflootit.ui.FloodItScreen.SETTINGS
import com.muen.gameflootit.ui.game.GameScreen
import com.muen.gameflootit.ui.settings.SettingsScreen
import com.muen.gameflootit.ui.theme.AppTheme
import com.muen.gameflootit.viewModel.GameViewModel

enum class FloodItScreen {
    GAME,
    SETTINGS,
}

@Composable
fun FloodItApp(
    gameViewModel: GameViewModel,
    navController: NavHostController,
    appTheme: AppTheme,
    onAppThemeSettingChanged: (newTheme: AppTheme) -> Unit
) {
    NavHost(navController = navController, startDestination = GAME.name) {
        composable(route = GAME.name) {
            GameScreen(
                gameState = gameViewModel.gameState,
                turn = gameViewModel.turn,
                maxTurns = gameViewModel.maxTurns,
                boardState = gameViewModel.board,
                onColorButtonClick = { color -> gameViewModel.chooseColor(color) },
                onRestartButtonClick = { gameViewModel.newGame() },
                onSettingsButtonClick = { navController.navigate(SETTINGS.name) }
            )
        }

        composable(route = SETTINGS.name) {
            SettingsScreen(
                appTheme = appTheme,
                onAppThemeSettingChanged = onAppThemeSettingChanged,
                onBackButtonClick = { navController.popBackStack() }
            )
        }
    }
}