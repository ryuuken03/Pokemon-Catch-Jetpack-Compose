package com.pokeapi.presentation.screen.mypokemon

import androidx.lifecycle.ViewModel
import com.pokeapi.data.source.local.entity.MyPokemonEntity
import com.pokeapi.domain.model.DBMyPokemon
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.usecase.GeListPokemonUseCase
import com.pokeapi.domain.usecase.mypokemon.GetListMyPokemon
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
 */
@HiltViewModel
class MyPokemonViewModel   @Inject constructor(
    private val getListMyPokemon: GetListMyPokemon
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<MutableList<DBMyPokemon>>> = MutableStateFlow(UiState.Loading())
    val uiState: StateFlow<UiState<MutableList<DBMyPokemon>>> = _uiState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun refresh(){
        _uiState.value = UiState.Loading()
    }

    fun getList() {
        _isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getListMyPokemon.execute(Unit)
                    .catch {
                        _isLoading.value = false
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect{ response ->
                        _isLoading.value = false
                        _uiState.value = UiState.Success(response)
                    }
            } catch (e: Exception) {
                _isLoading.value = false
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}