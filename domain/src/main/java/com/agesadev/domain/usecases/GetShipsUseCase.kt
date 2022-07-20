package com.agesadev.domain.usecases

import com.agesadev.domain.repository.ShipsRepository
import javax.inject.Inject

class GetShipsUseCase @Inject constructor(private val shipsRepository: ShipsRepository) {
    operator fun invoke() = shipsRepository.getListOfShips()
}