package com.agesadev.domain.repository

import com.agesadev.commons.utils.Resource
import com.agesadev.domain.model.Ships
import kotlinx.coroutines.flow.Flow

interface ShipsRepository {

    fun getListOfShips(): Flow<Resource<List<Ships>>>
}