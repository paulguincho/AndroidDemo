package com.example.clientsearchdemo.feature

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun DefaultText(inputText: String, fontSize: TextUnit?, fontColor: Color?, fontFamily: FontFamily? ) {
    Text(
        text = inputText, style = TextStyle(
            fontFamily = fontFamily ?: FontFamily.SansSerif,
            fontSize = fontSize ?: 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color =  fontColor ?: MaterialTheme.colors.primary
        )
    )
}