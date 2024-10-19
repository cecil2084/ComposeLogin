package com.example.composelogin.ui.screens.styles

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.example.composelogin.ui.theme.quicksandFamily

class StuddyPasswordVisualTransformation(
    private val mask: Char = '*'
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val maskedString = AnnotatedString(
            mask.toString().repeat(text.length), spanStyle = SpanStyle(
                fontFamily = quicksandFamily
            )
        )
        return TransformedText(maskedString, OffsetMapping.Identity)
    }
}