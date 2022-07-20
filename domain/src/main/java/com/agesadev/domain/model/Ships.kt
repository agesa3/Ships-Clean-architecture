package com.agesadev.domain.model

data class Ships(
    val active: Boolean,
    val home_port: String,
    val image: String,
    val roles: List<String>,
    val ship_id: String,
    val ship_name: String,
    val ship_type: String,
    val speed_kn: Int,
    val status: String,
    val newsUrl: String,
    val weight_kg: Int,
    val year_built: Int
)