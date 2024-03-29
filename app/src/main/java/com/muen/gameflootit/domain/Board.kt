package com.muen.gameflootit.domain

import kotlin.random.Random

class Board(config: BoardConfig){

    val width: Int = config.width
    val height: Int = config.height
    val colors: Int = config.colors

    private var board = emptyArray<Int>()

    init {
        randomize()
    }

    fun randomize() {
        for (i in 1..height)
            for (j in 1..width)
                board += Random.nextInt(1, colors + 1)
    }

    fun get(x: Int, y: Int): Int {
        require(x in 1..width) { "x must be in range 1..$width, was $x" }
        require (y in 1..height) { "y must be in range 1..$height, was $y" }

        return board[(y - 1) * width + (x - 1)]
    }

    fun set(x: Int, y: Int, value: Int) {
        require(x in 1..width) { "x must be in range 1..$width, your x is $x" }
        require(y in 1..height) { "y must be in range 1..$height, your y is $y" }
        require(value in 1..colors) { "value must be in range 1..$colors, your value is $value" }

        board[(y - 1) * width + (x - 1)] = value
    }

    fun values(): List<List<Int>> {
        val list = ArrayList<List<Int>>()
        for (y in 1..height) {
            val row = ArrayList<Int>()
            for (x in 1..width) {
                row.add(get(x, y))
            }
            list.add(row)
        }
        return list
    }
}

