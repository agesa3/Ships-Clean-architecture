package com.agesadev.data.remote.model

import com.google.gson.annotations.SerializedName

data class ShipsDto(

    val abs: Int,
    val active: Boolean,
    val attempted_landings: Any,
//    val `class`: Int,
    val course_deg: Any,
    val home_port: String,
    val image: String,
    val imo: Int,
    val missions: List<Mission>,
    val mmsi: Int,
    val position: Position,
    val roles: List<String>,
    val ship_id: String,
    val ship_model: Any,
    val ship_name: String,
    val ship_type: String,
    val speed_kn: Int,
    val status: String,
    val successful_landings: Any,
    val url: String,
    val weight_kg: Int,
    val weight_lbs: Int,
    val year_built: Int
)