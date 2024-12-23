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

package upv.dadm.ex26_settingsdatastore.data.greetings

import kotlinx.coroutines.flow.Flow
import upv.dadm.ex26_settingsdatastore.data.model.GreetingsPreferences

/**
 * Interface declaring the methods that the Repository exposes to ViewModels.
 */
interface GreetingsRepository {

    /**
     * Returns a Flow for the user name preference.
     */
    fun getUsername(): Flow<String>

    /**
     * Returns a Flow for the text color preference.
     */
    fun getTextColor(): Flow<String>

    /**
     * Returns a Flow for the icon visibility preference.
     */
    fun isIconVisible(): Flow<Boolean>

    /**
     * Returns a Flow for all the preferences.
     */
    fun getGreetingsPreferences(): Flow<GreetingsPreferences>

    /**
     * Sets the user name preference.
     */
    suspend fun setUsername(username: String)
    /**
     * Sets the text color preference.
     */
    suspend fun setTextColor(color: String)
    /**
     * Sets the icon visibility preference.
     */
    suspend fun setIconVisibility(isVisible: Boolean)

    /**
     * Returns the current value of the user name preference.
     */
    suspend fun getUsernameSnapshot(): String?

    /**
     * Returns the current value of the text color preference.
     */
    suspend fun getTextColorSnapshot(): String?

    /**
     * Returns the current value of the icon visibility preference.
     */
    suspend fun isIconVisibleSnapshot(): Boolean?
}