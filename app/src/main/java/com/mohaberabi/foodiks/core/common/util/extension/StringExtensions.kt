package com.mohaberabi.foodiks.core.common.util.extension


fun String.normalize(): String {
    val cleaned = this.trim().lowercase()
    val wildCard = "*${cleaned}*"
    return wildCard
}