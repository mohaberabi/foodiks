package com.mohaberabi.foodiks.core.data.di

import android.content.Context
import androidx.work.WorkManager
import com.mohaberabi.foodiks.core.data.source.syncer.FoodiksSyncWorker
import com.mohaberabi.foodiks.core.data.source.syncer.WorkManagerFoodiksSyncer
import com.mohaberabi.foodiks.core.domain.source.syncer.FoodiksSyncer
import com.mohaberabi.foodiks.core.common.util.DefaultDispatchersProvider
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.data.source.connectivity.AndroidAppConnectivityManager
import com.mohaberabi.foodiks.core.domain.source.connectivity.AppConnectivityManager
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val coreModule = module {

    singleOf(::DefaultDispatchersProvider) { bind<DispatchersProvider>() }
    single<WorkManager> {
        WorkManager.getInstance(get<Context>())
    }

    workerOf(::FoodiksSyncWorker)

    singleOf(::WorkManagerFoodiksSyncer) { bind<FoodiksSyncer>() }
    singleOf(::AndroidAppConnectivityManager) { bind<AppConnectivityManager>() }

}