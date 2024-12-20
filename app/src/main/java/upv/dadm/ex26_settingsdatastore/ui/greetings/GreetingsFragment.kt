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

package upv.dadm.ex26_settingsdatastore.ui.greetings

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import upv.dadm.ex26_settingsdatastore.R
import upv.dadm.ex26_settingsdatastore.databinding.FragmentGreetingsBinding

/**
 * Displays a greetings message to the user in a given color and, optionally, an icon.
 */
// The Hilt annotation @AndroidEntryPoint is required to receive dependencies from its parent class
@AndroidEntryPoint
class GreetingsFragment : Fragment(R.layout.fragment_greetings) {

    // Reference to the ViewModel
    private val viewModel: GreetingsViewModel by viewModels()

    // Backing property to resource binding
    private var _binding: FragmentGreetingsBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = FragmentGreetingsBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Update the user name and text color in the greetings message,
                // and the icon visibility whenever they change
                viewModel.preferences.collect { preferences ->
                    binding.tvGreetings.text = getString(
                        R.string.greetings_message,
                        preferences.userName.ifEmpty { getString(R.string.anonymous) }
                    )
                    binding.tvGreetings.setTextColor(
                        Color.parseColor(
                            preferences.color.ifEmpty {
                                getString(
                                    R.string.default_color
                                )
                            }
                        )
                    )
                    binding.ivGreetings.visibility =
                        if (preferences.isIconVisible) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }
}
