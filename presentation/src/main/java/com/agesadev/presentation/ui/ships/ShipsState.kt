package com.agesadev.presentation.ui.ships

import com.agesadev.domain.model.Ships

data class ShipsState(
    val isLoading: Boolean = false,
    val data: List<Ships> = emptyList(),
    val error: String? = null
)