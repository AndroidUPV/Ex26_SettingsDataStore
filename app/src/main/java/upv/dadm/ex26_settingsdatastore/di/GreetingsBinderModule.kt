/*
 * Copyright (c) 2022-2024 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex26_settingsdatastore.di

import androidx.preference.PreferenceDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import upv.dadm.ex26_settingsdatastore.data.greetings.GreetingsDataSource
import upv.dadm.ex26_settingsdatastore.data.greetings.GreetingsDataSourceImpl
import upv.dadm.ex26_settingsdatastore.data.greetings.GreetingsRepository
import upv.dadm.ex26_settingsdatastore.data.greetings.GreetingsRepositoryImpl
import upv.dadm.ex26_settingsdatastore.data.greetings.SettingsPreferenceDataStore
import javax.inject.Singleton

/**
 * Hilt module that determines how to provide required dependencies for interfaces.
 */
@Module
// The Hilt annotation @SingletonComponent creates and destroy instances following the lifecycle of the Application
@InstallIn(SingletonComponent::class)
abstract class GreetingsBinderModule {

    /**
     * Provides an instance of a GreetingDataStoreSource.
     */
    @Binds
    @Singleton
    abstract fun bindGreetingsDataSource(
        greetingsDataSourceImpl: GreetingsDataSourceImpl
    ): GreetingsDataSource

    /**
     * Provides an instance of a GreetingsRepository.
     */
    @Binds
    @Singleton
    abstract fun bindGreetingsRepository(
        greetingsRepositoryImpl: GreetingsRepositoryImpl
    ): GreetingsRepository

    /**
     * Provides an instance of a PreferenceDataStore.
     */
    @Binds
    // The Singleton annotation ensures that it will only exist a single instance of DataStore<Preferences>
    @Singleton
    abstract fun bindSettingsPreferenceDataStore(
        settingsPreferenceDataStore: SettingsPreferenceDataStore
    ): PreferenceDataStore
}