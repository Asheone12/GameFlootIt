package com.muen.gameflootit.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.muen.gameflootit.domain.Board
import com.muen.gameflootit.domain.BoardConfig
import com.muen.gameflootit.domain.Game
import com.muen.gameflootit.domain.GameState

class GameViewModel : ViewModel() {

    private lateinit var game: Game

    var board by mutableStateOf<List<List<Int>>>(listOf())
    private set
    var turn by mutableStateOf("")
    private set
    var maxTurns by mutableStateOf("")
    private set
    var gameState by mutableStateOf(GameState.RUN)
    private set

    init {
        newGame()
    }

    fun newGame() {
        game = Game(Board(BoardConfig(14, 14, 6)))
        updateUI()
    }

    private fun updateUI() {
        board = game.currentBoardState()
        turn = game.turn.toString()
        maxTurns = game.maxTurns.toString()
        gameState = game.state
    }

    fun chooseColor(value: Int) {
        game.chooseColor(value)
        updateUI()
    }
}