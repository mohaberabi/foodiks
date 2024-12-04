package com.mohaberabi.foodiks.core.common

import com.mohaberabi.foodiks.core.domain.model.AppResult

interface Refreshable {
    suspend fun refresh(
        forceRemote: Boolean,
    ): AppResult<Unit>
}