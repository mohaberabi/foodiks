package com.mohaberabi.foodiks.core.data.di

import android.content.Context
import androidx.work.WorkManager
import com.mohaberabi.foodiks.core.data.syncer.FoodiksSyncWorker
import com.mohaberabi.foodiks.core.data.syncer.WorkManagerFoodiksSyncer
import com.mohaberabi.foodiks.core.domain.syncer.FoodiksSyncer
import com.mohaberabi.foodiks.core.domain.util.DefaultDispatchersProvider
import com.mohaberabi.foodiks.core.domain.util.DispatchersProvider
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
}