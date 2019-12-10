package com.rkddlsgur983.test.view.login.domain

import androidx.annotation.StringRes

interface ApplicationDelegate {
    fun getString(@StringRes stringResId: Int): String
}