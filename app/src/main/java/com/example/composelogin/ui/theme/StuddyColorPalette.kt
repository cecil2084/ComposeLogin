package com.example.composelogin.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class StuddyColorPalette(
    val primary500: Color = Color.Unspecified,
    val primary600: Color = Color.Unspecified,
    val primary700: Color = Color.Unspecified,
    val primary800: Color = Color.Unspecified,
    val primary900: Color = Color.Unspecified,

    val success500: Color = Color.Unspecified,
    val success600: Color = Color.Unspecified,
    val success700: Color = Color.Unspecified,
    val success800: Color = Color.Unspecified,
    val success900: Color = Color.Unspecified,

    val warning500: Color = Color.Unspecified,
    val warning600: Color = Color.Unspecified,
    val warning700: Color = Color.Unspecified,
    val warning800: Color = Color.Unspecified,
    val warning900: Color = Color.Unspecified,

    val error500: Color = Color.Unspecified,
    val error600: Color = Color.Unspecified,
    val error700: Color = Color.Unspecified,
    val error800: Color = Color.Unspecified,
    val error900: Color = Color.Unspecified,

    val lightNeutral500: Color = Color.Unspecified,
    val lightNeutral600: Color = Color.Unspecified,
    val lightNeutral700: Color = Color.Unspecified,
    val lightNeutral800: Color = Color.Unspecified,
    val lightNeutral900: Color = Color.Unspecified,

    val darkNeutral500: Color = Color.Unspecified,
    val darkNeutral600: Color = Color.Unspecified,
    val darkNeutral700: Color = Color.Unspecified,
    val darkNeutral800: Color = Color.Unspecified,
    val darkNeutral900: Color = Color.Unspecified,
)

val primary500Light = Color(color = 0xFF86CEF7)
val primary600Light = Color(color = 0xFF52B9F3)
val primary700Light = Color(color = 0xFF2EABF1)
val primary800Light = Color(color = 0xFF2078A9)
val primary900Light = Color(color = 0xFF1C6893)

val success500Light = Color(color = 0xFFCEEFCE)
val success600Light = Color(color = 0xFF9DE09D)
val success700Light = Color(color = 0xFF4CD94B)
val success800Light = Color(color = 0xFF36C336)
val success900Light = Color(color = 0xFF2D912D)

val warning500Light = Color(color = 0xFFFCEEC6)
val warning600Light = Color(color = 0xFFF9DD8D)
val warning700Light = Color(color = 0xFFF3BB1B)
val warning800Light = Color(color = 0xFFB68C14)
val warning900Light = Color(color = 0xFF7A5D0D)

val error500Light = Color(color = 0xFFFBCDCF)
val error600Light = Color(color = 0xFFF89A9D)
val error700Light = Color(color = 0xFFE94F54)
val error800Light = Color(color = 0xFFDB2027)
val error900Light = Color(color = 0xFFC60B12)

val lightNeutral500Light = Color(color = 0xFF404040)
val lightNeutral600Light = Color(color = 0xFF595959)
val lightNeutral700Light = Color(color = 0xFF8C8C8C)
val lightNeutral800Light = Color(color = 0xFFB3B3B3)
val lightNeutral900Light = Color(color = 0xFFF5F5F5)

val darkNeutral500Light = Color(color = 0xFFF2F2F2)
val darkNeutral600Light = Color(color = 0xFFE5E5E5)
val darkNeutral700Light = Color(color = 0xFFBFBFBF)
val darkNeutral800Light = Color(color = 0xFF808080)
val darkNeutral900Light = Color(color = 0xFF333333)

val StuddyLightPalette = StuddyColorPalette(
    primary500 = primary500Light,
    primary600 = primary600Light,
    primary700 = primary700Light,
    primary800 = primary800Light,
    primary900 = primary900Light,

    success500 = success500Light,
    success600 = success600Light,
    success700 = success700Light,
    success800 = success800Light,
    success900 = success900Light,

    warning500 = warning500Light,
    warning600 = warning600Light,
    warning700 = warning700Light,
    warning800 = warning800Light,
    warning900 = warning900Light,

    error500 = error500Light,
    error600 = error600Light,
    error700 = error700Light,
    error800 = error800Light,
    error900 = error900Light,

    lightNeutral500 = lightNeutral500Light,
    lightNeutral600 = lightNeutral600Light,
    lightNeutral700 = lightNeutral700Light,
    lightNeutral800 = lightNeutral800Light,
    lightNeutral900 = lightNeutral900Light,

    darkNeutral500 = darkNeutral500Light,
    darkNeutral600 = darkNeutral600Light,
    darkNeutral700 = darkNeutral700Light,
    darkNeutral800 = darkNeutral800Light,
    darkNeutral900 = darkNeutral900Light
)

val StuddyDarkPalette = StuddyColorPalette(
    // TODO : DARK THEME TO BE ADDED IF DARK MODE IS TO BE
)

val LocalStuddyColors = staticCompositionLocalOf { StuddyColorPalette() }