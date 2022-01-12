package com.example.waybill.di

import android.app.Application
import androidx.room.Room
import com.example.waybill.data.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(
        app: Application,
        callback: Database.Callback
    ) = Room.databaseBuilder(app, Database::class.java, "my_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideCarDao(db: Database) = db.carsDao()

    @Provides
    fun provideWaybillDao(db: Database) = db.waybillsDao()


    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope