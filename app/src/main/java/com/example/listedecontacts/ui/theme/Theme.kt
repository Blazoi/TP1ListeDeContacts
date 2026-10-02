package com.example.listedecontacts.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = Red,
    onPrimary = White,

    secondary = LightGrey,
    onSecondary = Black,

    tertiary = BrightRed,
    onTertiary = White,

    background = Black,
    onBackground = White,

    surface = Charcoal,
    onSurface = White,

    surfaceVariant = DarkGrey,
    onSurfaceVariant = LightGrey,

    error = BrightRed,
    onError = White,

    outline = Grey,
    outlineVariant = DarkGrey
)


val LightColorScheme = darkColorScheme(
    primary = Red,
    onPrimary = White,

    secondary = LightGrey,
    onSecondary = Black,

    tertiary = BrightRed,
    onTertiary = White,

    background = Black,
    onBackground = White,

    surface = Charcoal,
    onSurface = White,

    surfaceVariant = DarkGrey,
    onSurfaceVariant = LightGrey,

    error = BrightRed,
    onError = White,

    outline = Grey,
    outlineVariant = DarkGrey
)



@Composable
fun ListeDeContactsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}