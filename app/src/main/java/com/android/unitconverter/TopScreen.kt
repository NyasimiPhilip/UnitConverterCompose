package com.android.unitconverter

import androidx.compose.runtime.Composable

@Composable
fun TopScreen(list: List<Conversion>) {
    ConversionMenu(list = list)
}