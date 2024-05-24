package com.pokeapi.presentation.screen.detailpokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pokeapi.domain.model.DBMyPokemon
import com.pokeapi.domain.model.ResponseCatchReleaseRename
import com.pokeapi.domain.model.ResponsePokemon
import com.pokeapi.domain.model.ResponsePokemonDetail
import com.pokeapi.domain.usecase.GeListPokemonUseCase
import com.pokeapi.domain.usecase.GePokemonDetailUseCase
import com.pokeapi.domain.usecase.GetCatchPokemonUseCase
import com.pokeapi.domain.usecase.GetReleasePokemonUseCase
import com.pokeapi.domain.usecase.GetRenamePokemonUseCase
import com.pokeapi.domain.usecase.mypokemon.DeleteMyPokemon
import com.pokeapi.domain.usecase.mypokemon.GetMyPokemon
import com.pokeapi.domain.usecase.mypokemon.InsertMyPokemon
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
class DetailPokemonViewModel  @Inject constructor(
    private val getDetailUseCase: GePokemonDetailUseCase,
    private val getCatchPokemonUseCase: GetCatchPokemonUseCase,
    private val getReleasePokemonUseCase: GetReleasePokemonUseCase,
    private val getRenamePokemonUseCase: GetRenamePokemonUseCase,
    private val insertMyPokemon: InsertMyPokemon,
    private val deleteMyPokemon: DeleteMyPokemon,
    private val getMyPokemon: GetMyPokemon
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<ResponsePokemonDetail>> = MutableStateFlow(UiState.Loading())
    val uiState: StateFlow<UiState<ResponsePokemonDetail>> = _uiState

    private val _uiStateCatchReleaseRename: MutableStateFlow<UiState<ResponseCatchReleaseRename>> = MutableStateFlow(UiState.Loading())
    val uiStateCatchReleaseRename: StateFlow<UiState<ResponseCatchReleaseRename>> = _uiStateCatchReleaseRename

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    var id = 0
    var name = ""
    var image = ""
    var nickname = ""
    var index = 0

    private val _isMyPokemon = MutableStateFlow(false)
    val isMyPokemon = _isMyPokemon.asStateFlow()
    private val _myPokemonNickname = MutableStateFlow("")
    val myPokemonNickname = _myPokemonNickname.asStateFlow()
    private val _indexMyPokemon = MutableStateFlow(0)
    val indexMyPokemon = _indexMyPokemon.asStateFlow()

    fun refresh(){
        _uiState.value = UiState.Loading()
    }

    fun refreshAlert(){
        _uiStateCatchReleaseRename.value = UiState.Loading()
    }

    fun getDetail(id :Int,name:String) {
        this.id = id
        this.name = name
        _isLoading.value = true
        checkMyPokemon(id)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getDetailUseCase.execute(id)
                    .catch {
                        _isLoading.value = false
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect{ response ->
                        _isLoading.value = false
                        image = response.image!!
                        _uiState.value = UiState.Success(response)
                    }
            } catch (e: Exception) {
                _isLoading.value = false
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun checkMyPokemon(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                getMyPokemon.execute(id.toString())
                    .catch {
                    }
                    .collect{ pokemon->
                        if(pokemon != null){
                            _isMyPokemon.value = true
                            _indexMyPokemon.value = pokemon.index!!
                            _myPokemonNickname.value = pokemon.nickname!!+if(pokemon.index!! > 0) "-"+pokemon.fibbonaci!! else ""
                            index = pokemon.index!!
                            nickname = pokemon.nickname!!
                        }else{
                        }
                    }
            } catch (e: Exception) {
            }

        }
    }

    fun getCatch() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var params = listOf(id.toString() , name)
                getCatchPokemonUseCase.execute(params)
                    .catch {
                        _uiStateCatchReleaseRename.value = UiState.Error(it.message.toString())
                    }
                    .collect{ response ->
                        if(response.status){
                            giveNickname("My"+name)
                        }
                        _uiStateCatchReleaseRename.value = UiState.Success(response)
                    }
            } catch (e: Exception) {
                _uiStateCatchReleaseRename.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun giveNickname(nickname: String){
        insertMyPokemon(id = id, name = name, image= image, nickname = nickname, index = 0, fibbonaci = 0)
        _isMyPokemon.value = true
        _myPokemonNickname.value = nickname
        this.nickname = nickname
    }

    fun insertMyPokemon(
        id: Int,
        name: String,
        image: String,
        nickname: String,
        index: Int,
        fibbonaci: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteMyPokemon.execute(id.toString()).apply {
                var params = listOf(id.toString() , name, image, nickname, index.toString(), fibbonaci.toString())
                insertMyPokemon.execute(params)
            }
        }
    }

    fun getRelease() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var params = listOf(id.toString() , name)
                getReleasePokemonUseCase.execute(params)
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect{ response ->
                        if(response.status){
                            deleteMyPokemon.execute(id.toString())
                            _isMyPokemon.value = false
                            _myPokemonNickname.value = ""
                        }
                        _uiStateCatchReleaseRename.value = UiState.Success(response)
                    }
            } catch (e: Exception) {
                _uiStateCatchReleaseRename.value = UiState.Error(e.message.toString())
            }
        }
    }
    fun getRename(index:Int) {
        this.index = index
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var params = listOf(id.toString() , name, index.toString(), nickname)
                getRenamePokemonUseCase.execute(params)
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect{ response ->
                        insertMyPokemon(id = id, name = name, image=image,nickname = nickname, index = response.index!!, fibbonaci = response.fibbonaci!!)
                        _indexMyPokemon.value = response.index!!
                        _isMyPokemon.value = true
                        _myPokemonNickname.value = nickname+if(response.index!! > 0) "-"+response.fibbonaci!! else ""
                        _uiStateCatchReleaseRename.value = UiState.Success(response)
                    }
            } catch (e: Exception) {
                _uiStateCatchReleaseRename.value = UiState.Error(e.message.toString())
            }
        }
    }
}