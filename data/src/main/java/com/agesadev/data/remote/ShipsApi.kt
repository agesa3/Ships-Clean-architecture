package com.agesadev.data.remote

import com.agesadev.data.remote.model.ShipsDto
import retrofit2.http.GET

interface ShipsApi {

    @GET("ships")
    suspend fun getShips(): List<ShipsDto>
}