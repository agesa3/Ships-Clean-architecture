package com.agesadev.presentation.ui.ships

import com.agesadev.domain.model.Ships

interface ItemOnClick {
    fun onItemClick(ship: Ships)
}