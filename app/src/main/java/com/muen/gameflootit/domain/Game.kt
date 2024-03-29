package com.muen.gameflootit.domain

class Game(private val board: Board) {

    private var currentColor = 0

    var state: GameState = GameState.RUN
    private set

    var turn = 0
    private set

    var maxTurns = 25

    init {
        currentColor = board.get(1, 1)

        while (isWin()) {
            board.randomize()
            currentColor = board.get(1, 1)
        }
    }

    fun chooseColor(value: Int) {
        require (value in 1..board.colors) { "value must be in range 1..${board.colors}, was $value" }

        if (value == currentColor)
            return

        recalculateGamePosition(value)
        turn++

        if (turn <= maxTurns && isWin())
            state = GameState.WIN
        else if (turn >= maxTurns)
            state = GameState.LOSE
    }

    fun currentBoardState(): List<List<Int>> {
        return board.values()
    }

    private fun recalculateGamePosition(color: Int) {
        changeColors(1, 1, color)
        currentColor = color
    }

    private fun changeColors(x: Int, y: Int, color: Int) {
        board.set(x, y, color)
        if (x - 1 >= 1 && board.get(x - 1, y) == currentColor)
            changeColors(x - 1, y, color)
        if (x + 1 <= board.width && board.get(x + 1, y) == currentColor)
            changeColors(x + 1, y, color)
        if (y - 1 >= 1 && board.get(x, y - 1) == currentColor)
            changeColors(x, y - 1, color)
        if (y + 1 <= board.height && board.get(x, y + 1) == currentColor)
            changeColors(x, y + 1, color)
    }

    private fun isWin(): Boolean {
        for (y in 1..board.height) {
            for (x in 1..board.width) {
                if (board.get(x, y) != currentColor)
                    return false
            }
        }
        return true
    }
}