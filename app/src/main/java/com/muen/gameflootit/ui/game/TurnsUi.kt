package com.muen.gameflootit.ui.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muen.gameflootit.R
import com.muen.gameflootit.ui.theme.FlooditTheme

@Composable
fun TurnsUi(
    modifier: Modifier = Modifier,
    turn: String,
    maxTurns: String
) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        text = stringResource(id = R.string.turns_ui_turns_label, turn, maxTurns),
    )
}

@Preview(showBackground = true)
@Composable
fun TurnsUiPreview() {
    FlooditTheme {
        TurnsUi(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            turn = "13",
            maxTurns = "24"
        )
    }
}