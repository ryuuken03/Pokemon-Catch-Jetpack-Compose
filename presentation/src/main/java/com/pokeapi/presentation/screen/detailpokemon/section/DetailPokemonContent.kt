package com.pokeapi.presentation.screen.detailpokemon.section

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.m_movie.presentation.ui.constant.primary2
import com.pokeapi.domain.model.ResponsePokemon
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.m_movie.presentation.ui.constant.primary
import com.pokeapi.domain.model.ResponseCatchReleaseRename
import com.pokeapi.domain.model.ResponsePokemonDetail
import com.pokeapi.presentation.component.dialog.AlertDialogConfirmation
import com.pokeapi.presentation.screen.detailpokemon.DetailPokemonViewModel
import com.pokeapi.presentation.util.UiState
import java.util.Locale
import com.pokeapi.presentation.R

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Composable
fun DetailPokemonContent(
    modifier: Modifier,
    data: ResponsePokemonDetail,
    viewModel: DetailPokemonViewModel,
) {

    val responseAlert =  remember { mutableStateOf(ResponseCatchReleaseRename()) }
    val showDialog =  remember { mutableStateOf(false) }
    if(showDialog.value){
        var textAction = if(responseAlert.value.status) "OK" else if(!responseAlert.value.nickname.isNullOrEmpty()) "SAVE" else "TRY AGAIN"
        var show = false
        if(responseAlert.value.message!!.contains("Catch",true)){
            textAction = "GIVE NICKNAME"
            show = true
        }
        AlertDialogConfirmation(
            title = "Status Pokemon",
            description = responseAlert.value.message!!,
            showDialog = showDialog,
            showEditText = show,
            textAction = textAction,
            setAction = {
                responseAlert.value = ResponseCatchReleaseRename()
                viewModel.refreshAlert()
                if(show){
                    viewModel.giveNickname(it)
                }
            }
        )
    }
    viewModel.uiStateCatchReleaseRename.collectAsState(initial = UiState.Loading()).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
            }

            is UiState.Success -> {
                responseAlert.value = uiState.data!!
                showDialog.value = true
            }

            is UiState.Error -> {
                AlertDialogConfirmation(
                    title = "Alert",
                    description = uiState.message!!,
                    showDialog = showDialog,
                    setAction = {
                        responseAlert.value = ResponseCatchReleaseRename()
                        viewModel.refreshAlert()
                    }
                )
            }
        }
    }
    val load by viewModel.isLoading.collectAsStateWithLifecycle()
    val isMyPokemon by viewModel.isMyPokemon.collectAsStateWithLifecycle()
    val myPokemonNickname by viewModel.myPokemonNickname.collectAsStateWithLifecycle()
    val indexMyPokemon by viewModel.indexMyPokemon.collectAsStateWithLifecycle()
    val isRefreshing =  remember { mutableStateOf(false) }
    val isShowButtonUp =  remember { mutableStateOf(false) }

    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return super.onPreScroll(available, source)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if(listState.firstVisibleItemIndex > 1){
                    isShowButtonUp.value = true
                }else{
                    isShowButtonUp.value = false
                }
                return super.onPostScroll(consumed, available, source)
            }
        }
    }
    Box(){
        SwipeRefresh(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value),
            onRefresh = {
                isRefreshing.value = true
                viewModel.refresh()
                isRefreshing.value = false
            }
        ) {
            LazyVerticalGrid(
                modifier = modifier
                    .nestedScroll(nestedScrollConnection)
                    .fillMaxSize(),
                columns = GridCells.Adaptive(90.dp),
//                columns = GridCells.Fixed(2),
                state = listState,
                content = {
                    items(data.types.size){
                        var type = data.types[it].type?.name!!
                        Box(
                            modifier = modifier
                                .padding(5.dp)
                                .background(
                                    color = primary,
                                    shape = RoundedCornerShape(50),
                                )
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 5.dp)
                                    .fillMaxWidth(),
                                text = type.replaceFirstChar {
                                    if (it.isLowerCase())
                                        it.titlecase(Locale.getDefault())
                                    else
                                        it.toString()
                                },
                                fontSize = 14.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ){
                        AsyncImage(
                            model = data.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .width(200.dp)
//                                .height(300.dp)
                        )
                    }
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ){
                        var name = "Wild "+data.name!!.replaceFirstChar {
                            if (it.isLowerCase())
                                it.titlecase(Locale.getDefault())
                            else
                                it.toString()
                        }
                        if(isMyPokemon){
                            name = myPokemonNickname
                        }
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp)
                                .fillMaxWidth(),
                            text = name,
                            fontSize = 16.sp,
                            color = primary,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    items(data.stats.size){
                        var name = data.stats[it].stat?.name!!
//                            var name2 = name
                        if(name.contains("Special-",true)){
                            name = name.replace("Special-","Sp-",true)
                        }
                        if(name.contains("Attack",true)){
                            name = name.replace("Attack","Atk",true)
                        }
                        if(name.contains("Defense",true)){
                            name = name.replace("Defense","Def",true)
                        }
                        var stat = name+" : "+ data.stats[it].base_stat
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 5.dp),
                            text = stat.replaceFirstChar {
                                if (it.isLowerCase())
                                    it.titlecase(Locale.getDefault())
                                else
                                    it.toString()
                            },
                            fontSize = 15.sp,
                            color = Color.Black,
//                                textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                contentPadding = PaddingValues(8.dp),
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            if(isMyPokemon){
                Row{
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(vertical = 10.dp, horizontal = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primary
                        ),
                        shape = RoundedCornerShape(10),
                        onClick = {
                            viewModel.getRelease()
                        },
                        contentPadding = PaddingValues()
                    ) {

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = "Release Pokemon",
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                            .padding(vertical = 10.dp, horizontal = 5.dp),
                        shape = RoundedCornerShape(10),
                        border = BorderStroke(1.dp, primary2),
                        onClick = {
                            viewModel.getRename(indexMyPokemon)
                        },
                        contentPadding = PaddingValues()
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = "Rename Pokemon",
                            fontSize = 14.sp,
                            color = primary2,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }else{
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primary
                    ),
                    shape = RoundedCornerShape(10),
                    onClick = {
                        viewModel.getCatch()
                    },
                    contentPadding = PaddingValues()
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Catch Pokemon",
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}