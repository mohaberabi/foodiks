package com.mohaberabi.foodiks.core.domain.source.syncer

import kotlinx.coroutines.flow.Flow

interface FoodiksSyncer {
    fun requestSync()
    fun isSyncing(): Flow<Boolean>
}