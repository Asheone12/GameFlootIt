package com.muen.gameflootit.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Red = Color(0xFFEF5350)

fun getColorByNumber(number: Int): Color {
    return when (number) {
        1 -> Color(0xFF42A5F5)
        2 -> Color(0xFFEF5350)
        3 -> Color(0xFF66BB6A)
        4 -> Color(0xFFEEB3FF)
        5 -> Color(0xFFFFEE58)
        6 -> Color(0xFF1E4969)
        else -> throw IllegalArgumentException("Invalid color number: $number")
    }
}