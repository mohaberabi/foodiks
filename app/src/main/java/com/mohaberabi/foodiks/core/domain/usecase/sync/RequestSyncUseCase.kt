package com.mohaberabi.foodiks.core.domain.usecase.sync

import com.mohaberabi.foodiks.core.domain.source.syncer.FoodiksSyncer


class RequestSyncUseCase(
    private val foodiksSyncer: FoodiksSyncer
) {
    operator fun invoke() = foodiksSyncer.requestSync()
}