package com.mohaberabi.foodiks.core.data.source.syncer

import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.mohaberabi.foodiks.core.data.source.syncer.FoodiksSyncHelper.SYNC_UID
import com.mohaberabi.foodiks.core.domain.source.syncer.FoodiksSyncer
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
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
            SYNC_UID,
            ExistingWorkPolicy.KEEP,
            FoodiksSyncHelper.startupSyncOneTimeRequest
        )
    }

    override fun isSyncing(): Flow<Boolean> {
        return workManager.getWorkInfosForUniqueWorkFlow(
            SYNC_UID,
        ).map { infos ->

            infos.any {
                Log.d("WorkManagerFoodiksSyncer", it.state.name)
                it.state == WorkInfo.State.RUNNING
            }
        }.conflate().flowOn(dispatchers.io)
    }
}