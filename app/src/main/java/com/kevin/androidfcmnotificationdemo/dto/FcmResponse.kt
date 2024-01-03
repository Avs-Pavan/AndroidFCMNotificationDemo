package com.kevin.androidfcmnotificationdemo.dto

data class FcmResponse(
    val canonical_ids: Int,
    val failure: Int,
    val multicast_id: Long,
    val success: Int
)