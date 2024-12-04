package com.mohaberabi.foodiks.core.data.di

import android.content.Context
import androidx.work.WorkManager
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.CoilUtils
import com.mohaberabi.foodiks.core.data.source.syncer.FoodiksSyncWorker
import com.mohaberabi.foodiks.core.data.source.syncer.WorkManagerFoodiksSyncer
import com.mohaberabi.foodiks.core.domain.source.syncer.FoodiksSyncer
import com.mohaberabi.foodiks.core.common.util.DefaultDispatchersProvider
import com.mohaberabi.foodiks.core.common.util.DispatchersProvider
import com.mohaberabi.foodiks.core.data.source.connectivity.AndroidAppConnectivityManager
import com.mohaberabi.foodiks.core.domain.source.connectivity.AppConnectivityManager
import io.ktor.client.HttpClient
import okhttp3.OkHttpClient
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
    single<ImageLoader> {
        val context = get<Context>()
        ImageLoader.Builder(context)
            .crossfade(true)
            .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.25).build() }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("img_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }.build()
    }

}

