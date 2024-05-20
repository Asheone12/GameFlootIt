package com.muen.gameflootit.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muen.gameflootit.R.string
import com.muen.gameflootit.domain.GameState
import com.muen.gameflootit.domain.GameState.RUN
import com.muen.gameflootit.ui.theme.FlooditTheme

@Composable
fun GameScreen(
    gameState: GameState,
    turn: String,
    maxTurns: String,
    boardState: List<List<Int>>,
    onColorButtonClick: (Int) -> Unit,
    onRestartButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(padding),
            verticalArrangement = Arrangement.Top
        ) {
            TurnsUi(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                turn = turn,
                maxTurns = maxTurns
            )

            BoardUi(
                modifier = Modifier
                    .weight(weight = 1.0f, fill = true)
                    .padding(8.dp, 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(40)
                    ),
                boardState = boardState
            )

            BoxWithConstraints(
                modifier = Modifier
                    .weight(weight = 0.5f, fill = true),
                contentAlignment = Alignment.Center,
            ) {
                when (gameState) {
                    GameState.WIN -> GameOverUi(
                        modifier = Modifier.fillMaxWidth(),
                        message = stringResource(string.game_over_win),
                        onRestartClick = onRestartButtonClick
                    )
                    GameState.LOSE -> GameOverUi(
                        modifier = Modifier.fillMaxWidth(),
                        message = stringResource(string.game_over_lose),
                        onRestartClick = onRestartButtonClick
                    )
                    else -> ButtonsUi(
                        modifier = Modifier
                            .padding(16.dp, 0.dp)
                            .fillMaxWidth(),
                        onButtonClick = onColorButtonClick
                    )
                }
            }
        }
    }
}

@Preview(
    heightDp = 500,
    widthDp = 400,
    showBackground = true
)
@Composable
fun GameUiPreview() {
    FlooditTheme {
        GameScreen(
            gameState = RUN,
            turn = "13",
            maxTurns = "24",
            boardState = listOf(
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
                listOf(1,2,3,4,5,6).shuffled(),
            ),
            onColorButtonClick = {},
            onRestartButtonClick = {},
            onSettingsButtonClick = {},
        )
    }
}