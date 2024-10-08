/*
 * Hnscloud Android Common Library
 *
 * SPDX-FileCopyrightText: 2022-2023 Hnscloud GmbH and Hnscloud contributors
 * SPDX-FileCopyrightText: 2022 Álvaro Brey <alvaro@alvarobrey.com>
 * SPDX-License-Identifier: MIT
 */
package com.hnscloud.android.common.ui.util

import android.content.Context
import android.content.res.Configuration

object PlatformThemeUtil {
    @JvmStatic
    fun isDarkMode(context: Context): Boolean =
        when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
}
