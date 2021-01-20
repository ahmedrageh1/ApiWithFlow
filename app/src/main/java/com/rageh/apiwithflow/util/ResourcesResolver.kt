package com.rageh.apiwithflow.util

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesResolver @Inject constructor(@ApplicationContext private val context: Context) {

    fun getString(@StringRes resId: Int) = context.getString(resId)

    fun getString(@StringRes resId: Int, vararg formatArgs: Any?) =
        context.getString(resId, *formatArgs)

}