package com.pokeapi.presentation.screen.listpokemon

import androidx.lifecycle.ViewModel
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.usecase.GeListPokemonUseCase
import com.pokeapi.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Created By Mohammad Toriq on 5/24/2024
 */@HiltViewModel
class ListPokemonViewModel  @Inject constructor(
    private val getListPokemonUseCase: GeListPokemonUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<ResponsePokemon>> = MutableStateFlow(UiState.Loading())
    val uiState: StateFlow<UiState<ResponsePokemon>> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _page = MutableStateFlow(1)
    val page = _page.asStateFlow()

    val limit = 20

    private val _isMax = MutableStateFlow(false)
    val isMax = _isMax.asStateFlow()

    fun refresh(){
        _page.value = 1
        _uiState.value = UiState.Loading()
    }

    fun addCounterPage(){
        _page.value ++
        getList()
    }

    fun getList() {
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var params = listOf(page.value.toString() , limit.toString())
                getListPokemonUseCase.execute(params)
                    .catch {
                        _isMax.value = true
                        _isLoading.value = false
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect{ response ->
                        var list = _uiState.value.data?.results
                        if(list == null){
                            list = arrayListOf()
                        }
                        if(page.value == 1){
                            list = arrayListOf()
                        }
                        response.results.forEach {
                            list.add(it)
                        }
                        response.results = list

                        if(page.value == response.max_page){
                            _isMax.value = true
                        }else{
                            _isMax.value = false
                        }
                        _isLoading.value = false
                        _uiState.value = UiState.Success(response)
                    }
            } catch (e: Exception) {
                _isMax.value = true
                _isLoading.value = false
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}