package dev.ericd.todoey.ui.resource

import androidx.annotation.StringRes

sealed class StringResource {

    data class Id(
        @StringRes
        val value: Int
    ) : StringResource()

    data class String(
        val value: kotlin.String
    ) : StringResource()

}