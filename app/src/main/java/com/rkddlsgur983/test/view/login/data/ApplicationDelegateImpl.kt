package com.rkddlsgur983.test.view.login.data

import android.app.Application
import com.rkddlsgur983.test.view.login.domain.ApplicationDelegate

class ApplicationDelegateImpl(private val application: Application): ApplicationDelegate {
    override fun getString(stringResId: Int): String {
        return application.getString(stringResId)
    }

    override fun getString(stringResId: Int, vararg formatArgs: Any): String {
        return application.resources.getString(stringResId, *formatArgs)
    }
}