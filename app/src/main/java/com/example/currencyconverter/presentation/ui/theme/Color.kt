package com.example.currencyconverter.presentation.ui.theme

import androidx.compose.ui.graphics.Color

object ThemeColors {
    object Night {
        val surface = Color.DarkGray
        val onSurface = Color(0xFFFFFFFF)
        val accentColor = Color(0xFFD15300)
        val onAccentColor = Color.White
        val secondary = Color(0xFF1E1F22)
        val onSecondary = Color(0xFF000000)
        val error = Color(0xFFC72727)
        val tertiary = Color(0xFF793500)
        val onTertiary = Color(0xFFFFFFFF)
    }

    object Day {
        val surface = Color(0xffffffff)
        val onSurface = Color(0xFF000000)
        val accentColor = Color(0xFFD15300)
        val onAccentColor = Color(0xFFFFFFFF)
        val secondary = Color(0xFFC56D29)
        val onSecondary = Color(0xFF000000)
        val error = Color(0xFFB00020)
        val tertiary = Color(0xFF793500)
        val onTertiary = Color(0xFFFFFFFF)
    }
}