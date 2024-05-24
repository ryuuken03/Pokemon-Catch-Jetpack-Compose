package com.pokeapi.presentation.screen.listpokemon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.m_movie.presentation.ui.constant.ResString
import com.m_movie.presentation.ui.constant.primary
import com.pokeapi.presentation.component.EmptyData
import com.pokeapi.presentation.component.ProgressLoading
import com.pokeapi.presentation.screen.listpokemon.section.ListPokemonContent
import com.pokeapi.presentation.util.UiState

/***
 * Created By Mohammad Toriq on 5/24/2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPokemonScreen(
    viewModel: ListPokemonViewModel = hiltViewModel(),
    navigateToDetail: (String,String) -> Unit
){
    val load by viewModel.isLoading.collectAsStateWithLifecycle()
    Scaffold(
        topBar ={
            Surface (shadowElevation = 1.dp){
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "My Pokemon",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = primary,
                        titleContentColor = Color.White,
                    ),
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(it)
                    .padding(5.dp)
            ) {

                viewModel.uiState.collectAsState(initial = UiState.Loading()).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            ProgressLoading()
                            if(!load){
                                viewModel.getList()
                            }
                        }

                        is UiState.Success -> {
                            if(uiState.data == null){
                                EmptyData(message = ResString.DATA_NOT_FOUND)
                            }else{
                                if(uiState.data.results.isNullOrEmpty()){
                                    EmptyData(message = ResString.NO_POKEMON)
                                }else{
                                    ListPokemonContent(
                                        modifier = Modifier,
                                        data = uiState.data,
                                        viewModel = viewModel,
                                        navigateToDetail = navigateToDetail
                                    )
                                }
                            }
                        }

                        is UiState.Error -> {
                            EmptyData(message = uiState.message!!)
                        }
                    }
                }
            }
        }
    )
}