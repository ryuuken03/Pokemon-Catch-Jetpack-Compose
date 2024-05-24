package com.pokeapi.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m_movie.presentation.ui.constant.primary

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Composable
fun EmptyData(message :String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Text(
            text = message,
            color = primary,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}