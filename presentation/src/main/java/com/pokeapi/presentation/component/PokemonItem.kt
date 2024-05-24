package com.pokeapi.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.m_movie.presentation.ui.constant.primary
import com.pokeapi.domain.model.Pokemon
import com.pokeapi.presentation.util.noRippleClickable
import java.util.Locale

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Composable
fun PokemonItem (
    modifier: Modifier,
    data: Pokemon,
    onClick: () -> Unit = {}
){
    OutlinedButton(
        modifier = Modifier
            .padding(all = 3.dp)
        ,
        contentPadding = PaddingValues(),
        border = BorderStroke(0.dp, Color.White),
        shape = RoundedCornerShape(4.dp),
        onClick = fun(){
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable{
                    onClick()
                }
        ){
            AsyncImage(
                model = data.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 10.dp),
                text = data.name!!.replaceFirstChar {
                    if (it.isLowerCase())
                        it.titlecase(Locale.getDefault())
                    else
                        it.toString()
                },
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}
