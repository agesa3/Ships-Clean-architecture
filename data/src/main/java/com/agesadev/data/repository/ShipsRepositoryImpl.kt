package com.agesadev.data.repository

import com.agesadev.commons.utils.Resource
import com.agesadev.data.mappers.toShips
import com.agesadev.data.remote.ShipsApi
import com.agesadev.domain.model.Ships
import com.agesadev.domain.repository.ShipsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ShipsRepositoryImpl @Inject constructor(
    private val shipsApi: ShipsApi
) : ShipsRepository {
    override fun getListOfShips(): Flow<Resource<List<Ships>>> = flow {
        try {
            emit(Resource.Loading())
            val theShips = shipsApi.getShips().map {
                it.toShips()
            }
            emit(Resource.Success(theShips))
        } catch (e: Exception) {
            emit(Resource.Error("An Error Occurred"))
        } catch (e: Exception) {
            emit(Resource.Error("Opps!! Failed.PLease Retry"))
        }

    }

}