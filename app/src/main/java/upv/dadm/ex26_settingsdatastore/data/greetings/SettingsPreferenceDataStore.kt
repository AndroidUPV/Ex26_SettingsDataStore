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

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Provides an implementation of the PreferenceDataStore to store the user
 * Preferences using a DataStore instead of SharedPreferences.
 */
class SettingsPreferenceDataStore @Inject constructor(
    private val greetingsRepository: GreetingsRepository
) : PreferenceDataStore() {

    /**
     * Puts the user name or text color in the DataStore.
     */
    override fun putString(key: String?, value: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                "prefs_username" -> greetingsRepository.setUsername(value ?: "")
                "prefs_color" -> greetingsRepository.setTextColor(value ?: "")
                else -> {}
            }
        }
    }

    /**
     * Puts the icon visibility in the DataStore.
     */
    override fun putBoolean(key: String?, value: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            when (key) {
                "prefs_isIconVisible" -> greetingsRepository.setIconVisibility(value)
                else -> {}
            }
        }
    }

    /**
     * Gets the user name or text color from the DataStore.
     */
    override fun getString(key: String?, defValue: String?): String {
        var result = ""
        runBlocking(Dispatchers.IO) {
            when (key) {
                "prefs_username" -> result = greetingsRepository.getUsernameSnapshot()
                "prefs_color" -> result = greetingsRepository.getTextColorSnapshot()
                else -> {}
            }
        }
        return result
    }


    /**
     * Gets the icon visibility from the DataStore.
     */
    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        var result = false
        runBlocking(Dispatchers.IO) {
            when (key) {
                "prefs_isIconVisible" -> result = greetingsRepository.isIconVisibleSnapshot()
                else -> {}
            }
        }
        return result
    }

}