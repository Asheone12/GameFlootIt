package com.muen.gameflootit

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.muen.gameflootit.ui.FloodItApp
import com.muen.gameflootit.ui.theme.AppTheme.DARK
import com.muen.gameflootit.ui.theme.AppTheme.LIGHT
import com.muen.gameflootit.ui.theme.AppTheme.SYSTEM
import com.muen.gameflootit.ui.theme.FlooditTheme
import com.muen.gameflootit.viewModel.GameViewModel
import com.muen.gameflootit.viewModel.ThemeViewModel
import com.muen.gameflootit.viewModel.ThemeViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val gameViewModel by viewModels<GameViewModel>()
            val themeViewModel by viewModels<ThemeViewModel> { ThemeViewModelFactory(dataStore) }
            val appTheme = themeViewModel.theme.collectAsState()
            FlooditTheme(darkTheme = when (appTheme.value) {
                LIGHT -> false
                DARK -> true
                SYSTEM -> isSystemInDarkTheme()
            }) {
                Surface(color = MaterialTheme.colors.background) {
                    FloodItApp(
                        gameViewModel = gameViewModel,
                        navController = navController,
                        appTheme = appTheme.value,
                        onAppThemeSettingChanged = { themeViewModel.setTheme(it) }
                    )
                }
            }
        }
    }
}