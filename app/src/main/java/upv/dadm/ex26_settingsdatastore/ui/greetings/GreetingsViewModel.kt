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

package upv.dadm.ex26_settingsdatastore.ui.greetings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import upv.dadm.ex26_settingsdatastore.data.greetings.GreetingsRepository
import upv.dadm.ex26_settingsdatastore.data.model.GreetingsPreferences
import javax.inject.Inject

/**
 * Holds information about the greetings screen.
 */
// The Hilt annotation @HiltEntryPoint is required to receive dependencies from its parent class
@HiltViewModel
class GreetingsViewModel @Inject constructor(
    greetingsRepository: GreetingsRepository
) : ViewModel() {

    // User preferences (user name, text color, icon visibility)
    val preferences = greetingsRepository.getGreetingsPreferences().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = GreetingsPreferences("", "", false)
    )
}