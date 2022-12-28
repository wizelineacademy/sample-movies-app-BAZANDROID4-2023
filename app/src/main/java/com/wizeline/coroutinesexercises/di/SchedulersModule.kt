package com.wizeline.coroutinesexercises.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {

    @Provides
    @IoScheduler
    fun providesIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @MainScheduler
    fun providesMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainScheduler