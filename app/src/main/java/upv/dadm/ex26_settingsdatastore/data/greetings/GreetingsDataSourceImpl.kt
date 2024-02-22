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

package upv.dadm.ex26_settingsdatastore.data.greetings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import upv.dadm.ex26_settingsdatastore.data.model.GreetingsPreferences
import java.io.IOException
import javax.inject.Inject

/**
 * Data source that provides access to DataStore to get the user's preferences
 * about the greetings screen.
 * It implements the GreetingsDataStoreDataSource interface.
 */
class GreetingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : GreetingsDataSource {

    // Constant defining the preferences keys
    private object PreferenceKeys {
        val USER_NAME = stringPreferencesKey("prefs_username")
        val TEXT_COLOR = stringPreferencesKey("prefs_color")
        val ICON_VISIBILITY = booleanPreferencesKey("prefs_isIconVisible")
    }

    /**
     * Returns a Flow for the user name preference.
     */
    override fun getUsername(): Flow<String> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[PreferenceKeys.USER_NAME] ?: ""
        }

    /**
     * Returns the current value of the user name preference.
     */
    override suspend fun getUsernameSnapshot(): String =
        dataStore.data.first()[PreferenceKeys.USER_NAME] ?: ""

    /**
     * Returns a Flow for the text color preference.
     */
    override fun getTextColor(): Flow<String> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[PreferenceKeys.TEXT_COLOR] ?: ""
        }

    /**
     * Returns the current value of the text color preference.
     */
    override suspend fun getTextColorSnapshot(): String =
        dataStore.data.first()[PreferenceKeys.TEXT_COLOR] ?: ""

    /**
     * Returns a Flow for the icon visibility preference.
     */
    override fun isIconVisible(): Flow<Boolean> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[PreferenceKeys.ICON_VISIBILITY] ?: true
        }

    /**
     * Returns the current value of the icon visibility preference.
     */
    override suspend fun isIconVisibleSnapshot(): Boolean =
        dataStore.data.first()[PreferenceKeys.ICON_VISIBILITY] ?: true

    /**
     * Returns a Flow for all the preferences.
     */
    override fun getGreetingsPreferences(): Flow<GreetingsPreferences> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            mapToGreetingsPreferences(preferences)
        }

    /**
     * Sets the user name preference.
     */
    override suspend fun setUsername(username: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.USER_NAME] = username
        }
    }

    /**
     * Sets the text color preference.
     */
    override suspend fun setTextColor(color: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.TEXT_COLOR] = color
        }
    }

    /**
     * Sets the icon visibility preference.
     */
    override suspend fun setIconVisibility(isVisible: Boolean) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.ICON_VISIBILITY] = isVisible
        }
    }

    /**
     * Maps the provided Preferences into a GreetingsPreferences.
     */
    private fun mapToGreetingsPreferences(preferences: Preferences) =
        GreetingsPreferences(
            preferences[PreferenceKeys.USER_NAME] ?: "",
            preferences[PreferenceKeys.TEXT_COLOR] ?: "",
            preferences[PreferenceKeys.ICON_VISIBILITY] ?: true
        )
}