package com.agesadev.data.mappers

import com.agesadev.data.remote.model.ShipsDto
import com.agesadev.domain.model.Ships

fun ShipsDto.toShips(): Ships {

    return Ships(
        active = active,
        home_port = home_port,
        image = image,
        roles = roles,
        ship_id = ship_id,
        ship_name = ship_name,
        ship_type = ship_type,
        speed_kn = speed_kn,
        status = status,
        newsUrl = url,
        weight_kg = weight_kg,
        year_built = year_built
    )
}
