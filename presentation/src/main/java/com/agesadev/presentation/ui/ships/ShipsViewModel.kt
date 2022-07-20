package com.agesadev.presentation.ui.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agesadev.commons.utils.Resource
import com.agesadev.domain.usecases.GetShipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShipsViewModel @Inject constructor(
    private val getShipsUseCase: GetShipsUseCase
) : ViewModel() {

    private val _ships = MutableStateFlow(ShipsState())
    val ships: StateFlow<ShipsState> = _ships


    init {
        getShips()
    }

    private fun getShips() {
        viewModelScope.launch {
            getShipsUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _ships.value = ShipsState(ships = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _ships.value = ShipsState(error = "Failed To Load")
                    }
                    is Resource.Loading -> {
                        _ships.value = ShipsState(isLoading = true)
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}