package com.pokeapi.presentation.screen.listpokemon.section

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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.m_movie.presentation.ui.constant.primary2
import com.pokeapi.domain.model.ResponsePokemon
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokeapi.presentation.component.PokemonItem
import com.pokeapi.presentation.screen.listpokemon.ListPokemonViewModel
import com.pokeapi.presentation.util.OnBottomReached
import com.pokeapi.presentation.util.noRippleClickable
import kotlinx.coroutines.launch
import java.util.Locale

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@Composable
fun ListPokemonContent(
    modifier: Modifier,
    data: ResponsePokemon,
    viewModel: ListPokemonViewModel,
    navigateToDetail: (String,String) -> Unit,
) {
    val max by viewModel.isMax.collectAsStateWithLifecycle()
    val load by viewModel.isLoading.collectAsStateWithLifecycle()
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
//                columns = GridCells.Adaptive(128.dp),
                columns = GridCells.Fixed(3),
                state = listState,
                content = {
                    items (count = data.results.size){ index ->
                        var pokemon = data.results[index]
                        PokemonItem(
                            modifier = Modifier,
                            data = pokemon,
                            onClick = {
                                navigateToDetail(pokemon.id.toString(),
                                    pokemon.name!!.replaceFirstChar {
                                    if (it.isLowerCase())
                                        it.titlecase(Locale.getDefault())
                                    else
                                        it.toString()
                                },)
                            }
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
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if(isShowButtonUp.value){
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = primary2,
                                shape = RoundedCornerShape(100),
                            )
                            .noRippleClickable {
                                coroutineScope.launch {
                                    listState.scrollToItem(index = 0)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "scrollUp",
                            tint = Color.White,
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .padding(5.dp),
                        )
                    }
                }
            }
        }
    }
    listState.OnBottomReached (buffer = 2){
        if(!max && !load){
            viewModel.addCounterPage()
        }
    }
}