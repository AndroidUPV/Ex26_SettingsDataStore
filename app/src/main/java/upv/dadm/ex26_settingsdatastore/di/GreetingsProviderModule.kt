/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex26_settingsdatastore.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import upv.dadm.ex26_settingsdatastore.data.greetings.GreetingsRepository
import upv.dadm.ex26_settingsdatastore.data.greetings.SettingsPreferenceDataStore
import javax.inject.Singleton

/**
 * Hilt module that determines how to provide required dependencies
 * for third party components and Builders.
 */
@Module
@InstallIn(SingletonComponent::class)
class GreetingsProviderModule {

    // Constant for the name of the preference file
    private object Constants {
        const val PREFERENCE_FILE = "GreetingsPreferences"
    }

    /**
     * Provides an instance of a DataStore<Preferences>.
     */
    @Provides
    // The Singleton annotation ensures that it will only exist a single instance of DataStore<Preferences>
    @Singleton
    fun provideGreetingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            // If there is any problem when reading data, send an empty Preferences
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            // DataStore will be empty when migrations apply
            migrations = listOf(),
            // Scope for IO operations
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            // File where preferences will be stored/retrieved
            produceFile = { context.preferencesDataStoreFile(Constants.PREFERENCE_FILE) }
        )
    }

    /**
     * Provides an instance of a PreferenceDataStore.
     */
    @Provides
    // The Singleton annotation ensures that it will only exist a single instance of DataStore<Preferences>
    @Singleton
    fun provideSettingsPreferenceDataStore(
        greetingsRepository: GreetingsRepository
    ): PreferenceDataStore =
        SettingsPreferenceDataStore(greetingsRepository)

}