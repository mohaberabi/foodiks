package com.mohaberabi.foodiks.core.data.syncer

import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.mohaberabi.foodiks.core.domain.syncer.FoodiksSyncer
import com.mohaberabi.foodiks.core.domain.util.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class WorkManagerFoodiksSyncer(
    private val workManager: WorkManager,
    private val dispatchers: DispatchersProvider,
) : FoodiksSyncer {
    override fun requestSync() {
        workManager.enqueueUniqueWork(
            FoodiksSyncHelper.SYNC_UID,
            ExistingWorkPolicy.KEEP,
            FoodiksSyncHelper.startupSyncOneTimeRequest
        )
    }

    override fun isSyncing(): Flow<Boolean> {
        return workManager.getWorkInfosForUniqueWorkFlow(
            FoodiksSyncHelper.SYNC_UID,
        ).map { infos ->
            infos.any { it.state == WorkInfo.State.RUNNING }
        }.conflate().flowOn(dispatchers.io)
    }
}