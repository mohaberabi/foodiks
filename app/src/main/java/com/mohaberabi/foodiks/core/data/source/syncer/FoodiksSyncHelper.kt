package com.mohaberabi.foodiks.core.data.source.syncer

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy


object FoodiksSyncHelper {
    private val SyncConstraints
        get() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    val startupSyncOneTimeRequest = OneTimeWorkRequestBuilder<FoodiksSyncWorker>()
        .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
        .setConstraints(SyncConstraints)
        .build()


    const val SYNC_UID = "foodiksSync"
}