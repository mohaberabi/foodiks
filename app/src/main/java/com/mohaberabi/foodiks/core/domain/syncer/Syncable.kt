package com.mohaberabi.foodiks.core.domain.syncer

import com.mohaberabi.foodiks.core.domain.model.AppResult

interface Syncable {
    suspend fun sync(): AppResult<Unit>
}