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

package upv.dadm.ex26_settingsdatastore.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceFragmentCompat
import dagger.hilt.android.AndroidEntryPoint
import upv.dadm.ex26_settingsdatastore.R
import javax.inject.Inject

/**
 * Displays an Android-like settings screen for managing
 * the user's preferences about the greetings screen.
 */
@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var preferenceDataStore: PreferenceDataStore

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Sets the PreferenceDataStore to be used for preferences
        preferenceManager.preferenceDataStore = preferenceDataStore
        // Create the preferences hierarchy from the XML resource file
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}