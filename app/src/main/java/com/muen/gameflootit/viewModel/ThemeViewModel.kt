package com.muen.gameflootit.viewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.muen.gameflootit.ui.theme.AppTheme
import com.muen.gameflootit.ui.theme.AppTheme.DARK
import com.muen.gameflootit.ui.theme.AppTheme.LIGHT
import com.muen.gameflootit.ui.theme.AppTheme.SYSTEM

class ThemeViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    val theme get() = _theme.asStateFlow()

    private val _theme = MutableStateFlow(SYSTEM)
    private val themePref = stringPreferencesKey("app_theme")

    init {
        viewModelScope.launch {
            dataStore.data.collect { preferences ->
                _theme.tryEmit(
                    parseThemePreference(preferences[themePref])
                )
            }
        }
    }

    fun setTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[themePref] = appTheme.toPreferenceValue()
            }
        }
    }

    private fun parseThemePreference(text: String?): AppTheme =
        when (text) {
            LIGHT_PREF -> LIGHT
            DARK_PREF -> DARK
            else -> SYSTEM
        }

    private fun AppTheme.toPreferenceValue(): String =
        when (this) {
            SYSTEM -> SYSTEM_PREF
            LIGHT -> LIGHT_PREF
            DARK -> DARK_PREF
        }

    private companion object {

        const val SYSTEM_PREF = "system"
        const val LIGHT_PREF = "light"
        const val DARK_PREF = "dark"
    }
}