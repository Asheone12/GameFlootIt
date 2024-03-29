package com.muen.gameflootit.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.muen.gameflootit.ui.theme.FlooditTheme
import com.muen.gameflootit.ui.theme.getColorByNumber

@Composable
fun ButtonsUi(
    modifier: Modifier = Modifier,
    onButtonClick: (Int) -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val buttonSize = getButtonSize(maxWidth, maxHeight)
        val verticalPadding = buttonSize / 4
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            val rowCount = 2
            val buttonsInRowCount = 3

            repeat(rowCount) {
                val baseButtonNumber = it * buttonsInRowCount
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, verticalPadding),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(buttonsInRowCount) {
                        val buttonNumber = it + 1 + baseButtonNumber
                        FloatingActionButton(
                            modifier = Modifier.size(buttonSize),
                            onClick = { onButtonClick.invoke(buttonNumber) },
                            backgroundColor = getColorByNumber(buttonNumber)
                        ) {}
                    }
                }
            }
        }
    }
}

private fun getButtonSize(maxWidth: Dp, maxHeight: Dp): Dp =
    if (maxWidth > 600.dp) {
        getSizeLessThan(96.dp, maxHeight)
    } else {
        getSizeLessThan(64.dp, maxHeight)
    }

private fun getSizeLessThan(default: Dp, maxHeight: Dp): Dp =
    if (default < maxHeight / 3) default else maxHeight / 3

@Preview(
    heightDp = 200,
    widthDp = 400,
    showBackground = true
)
@Composable
fun ButtonsUiPreview() {
    FlooditTheme {
        ButtonsUi(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp),
        ) { }
    }
}