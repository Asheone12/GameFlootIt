package com.muen.gameflootit.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.muen.gameflootit.R
import com.muen.gameflootit.R.string
import com.muen.gameflootit.ui.theme.AppTheme

@Composable
fun SettingsScreen(
    appTheme: AppTheme,
    onAppThemeSettingChanged: (newTheme: AppTheme) -> Unit,
    onBackButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.settings)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackButtonClick.invoke() }) {
                        Icon(
                            imageVector = Filled.ArrowBack,
                            contentDescription = stringResource(id = string.settings_back_content_description)
                        )
                    }
                },
                contentColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ThemeSettings(
                selected = appTheme,
                onSelectTheme = onAppThemeSettingChanged,
            )
        }
    }
}
