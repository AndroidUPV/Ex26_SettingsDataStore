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

package upv.dadm.ex26_settingsdatastore.data.model

/**
 * An object containing the user's preference about
 * her user name, the text color, and whether to display an icon.
 */
data class GreetingsPreferences(
    val userName: String,
    val color: String,
    val isIconVisible: Boolean
)
