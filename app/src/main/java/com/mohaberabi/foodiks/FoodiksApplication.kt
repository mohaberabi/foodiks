package com.mohaberabi.foodiks

import android.app.Application
import androidx.work.Configuration
import com.mohaberabi.foodiks.core.data.di.coreLocalDataSourceModule
import com.mohaberabi.foodiks.core.data.di.coreModule
import com.mohaberabi.foodiks.core.data.di.coreRepositoryModule
import com.mohaberabi.foodiks.core.data.di.coreUseCaseModule
import com.mohaberabi.foodiks.core.data.di.coreViewModelModule
import com.mohaberabi.foodiks.core.data.di.databaseModule
import com.mohaberabi.foodiks.core.data.di.networkModule
import com.mohaberabi.foodiks.core.domain.source.syncer.FoodiksSyncer
import com.mohaberabi.foodiks.core.domain.usecase.sync.RequestSyncUseCase
import com.mohaberabi.foodiks.features.tables.data.di.tablesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin


class FoodiksApplication : Application(), Configuration.Provider, KoinComponent {

    private val requestSync by inject<RequestSyncUseCase>()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FoodiksApplication)
            androidLogger()
            modules(
                coreModule,
                databaseModule,
                networkModule,
                coreLocalDataSourceModule,
                coreRepositoryModule,
                coreUseCaseModule,
                tablesModule,
                coreViewModelModule
            )
        }
        requestSync()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(KoinWorkerFactory()).build()
}