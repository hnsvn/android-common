/*
 * Hnscloud Android Common Library
 *
 * SPDX-FileCopyrightText: 2022-2023 Hnscloud GmbH and Hnscloud contributors
 * SPDX-FileCopyrightText: 2023 Andy Scherzinger <info@andy-scherzinger.de>
 * SPDX-FileCopyrightText: 2023 Stefan Niedermann <info@niedermann.it>
 * SPDX-License-Identifier: MIT
 */
package com.hnscloud.android.common.sample

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hnscloud.android.common.sample.databinding.ActivityMainBinding
import com.hnscloud.android.common.ui.color.ColorUtil
import com.hnscloud.android.common.ui.theme.MaterialSchemes
import com.hnscloud.android.common.ui.theme.utils.AndroidViewThemeUtils
import com.hnscloud.android.common.ui.theme.utils.ColorRole
import com.hnscloud.android.common.ui.theme.utils.MaterialViewThemeUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Color should be fetched from the server capabilities or another proper source
        binding.btn.setOnClickListener { _ ->
            try {
                mainViewModel.color.value = Color.parseColor("#${binding.color.text}")
            } catch (_: java.lang.IllegalArgumentException) {
                Toast.makeText(
                    this,
                    "#${binding.color.text} is not a valid color.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        mainViewModel.color.observe(this) { applyTheme(it) }
    }

    private fun applyTheme(color: Int) {
        // Define your MaterialSchemes and ColorUtil
        val schemes = MaterialSchemes.Companion.fromColor(color)
        val colorUtil = ColorUtil(this)

        // Use them to instantiate ThemUtils you need
        val platform = AndroidViewThemeUtils(schemes, colorUtil)
        val material = MaterialViewThemeUtils(schemes, colorUtil)
        // val androidx = AndroidXViewThemeUtils(schemes, platform)
        // val dialog = DialogViewThemeUtils(schemes)

        // Use the methods of the ThemeUtils to apply the actual theme.
        // For a consistent User Experience it is necessary to apply the theme to *every* UI element
        platform.colorViewBackground(binding.container, ColorRole.SURFACE)
        platform.colorTextView(binding.headlineLib, ColorRole.PRIMARY)
        platform.colorTextView(binding.headlineModuleUi, ColorRole.SECONDARY)
        platform.themeStatusBar(this)
        material.colorTextInputLayout(binding.colorTil)
        material.themeExtendedFAB(binding.btn)
        material.themeChipAssist(binding.assistChip)
        material.themeChipInput(binding.inputChip)
        material.themeChipSuggestion(binding.suggestionChip)
        material.themeChipFilter(binding.filterChip)
    }
}
