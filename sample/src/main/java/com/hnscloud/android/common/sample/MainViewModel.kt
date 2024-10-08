/*
 * Hnscloud Android Common Library
 *
 * SPDX-FileCopyrightText: 2022-2023 Hnscloud GmbH and Hnscloud contributors
 * SPDX-FileCopyrightText: 2023 Stefan Niedermann <info@niedermann.it>
 * SPDX-License-Identifier: MIT
 */
package com.hnscloud.android.common.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val color = MutableLiveData<Int>()
}
