package com.agesadev.presentation.ui.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agesadev.commons.utils.Resource
import com.agesadev.domain.usecases.GetShipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
            getShipsUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _ships.value = ShipsState(data = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _ships.value = ShipsState(error = result.message ?: "Error getting ships")

                    }
                    is Resource.Loading -> {
                        _ships.value = ShipsState(isLoading = true)
                    }
                }

            }
        }
    }

    //clear viewmodel state and perfomr refresh of fetching data
     fun refresh() {
        _ships.value = ShipsState()
        getShips()
    }
}