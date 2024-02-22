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

import kotlinx.coroutines.flow.Flow
import upv.dadm.ex26_settingsdatastore.data.model.GreetingsPreferences
import javax.inject.Inject

/**
 * Repository for retrieving the user's preference about the greetings screen.
 * It implements the GreetingsRepository interface.
 */
class GreetingsRepositoryImpl @Inject constructor(
    private val greetingsDataSource: GreetingsDataSource
) : GreetingsRepository {

    /**
     * Returns a Flow for the user name preference.
     */
    override fun getUsername(): Flow<String> =
        greetingsDataSource.getUsername()

    /**
     * Returns a Flow for the text color preference.
     */
    override fun getTextColor(): Flow<String> =
        greetingsDataSource.getTextColor()

    /**
     * Returns a Flow for the icon visibility preference.
     */
    override fun isIconVisible(): Flow<Boolean> =
        greetingsDataSource.isIconVisible()

    /**
     * Returns a Flow for all the preferences.
     */
    override fun getGreetingsPreferences(): Flow<GreetingsPreferences> =
        greetingsDataSource.getGreetingsPreferences()

    /**
     * Sets the user name preference.
     */
    override suspend fun setUsername(username: String) {
        greetingsDataSource.setUsername(username)
    }

    /**
     * Sets the text color preference.
     */
    override suspend fun setTextColor(color: String) {
        greetingsDataSource.setTextColor(color)
    }

    /**
     * Sets the icon visibility preference.
     */
    override suspend fun setIconVisibility(isVisible: Boolean) {
        greetingsDataSource.setIconVisibility(isVisible)
    }

    /**
     * Returns the current value of the user name preference.
     */
    override suspend fun getUsernameSnapshot(): String =
        greetingsDataSource.getUsernameSnapshot()

    /**
     * Returns the current value of the text color preference.
     */
    override suspend fun getTextColorSnapshot(): String =
        greetingsDataSource.getTextColorSnapshot()

    /**
     * Returns the current value of the icon visibility preference.
     */
    override suspend fun isIconVisibleSnapshot(): Boolean =
        greetingsDataSource.isIconVisibleSnapshot()

}