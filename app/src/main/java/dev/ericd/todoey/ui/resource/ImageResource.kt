package dev.ericd.todoey.ui.resource

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.ImageBitmap

sealed class ImageResource {

    data class Id(
        @DrawableRes
        val value: Int
    ) : ImageResource()

    data class Bitmap(
        val value: ImageBitmap
    ) : ImageResource()

}