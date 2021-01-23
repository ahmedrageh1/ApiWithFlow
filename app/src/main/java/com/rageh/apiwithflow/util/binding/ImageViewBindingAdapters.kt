package com.rageh.apiwithflow.util.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageViewBindingAdapters @Inject constructor(private val picasso: Picasso) {

    @BindingAdapter("imageUrl", "error")
    fun loadImage(view: ImageView, url: String?, error: Drawable) {
        if (url == null) {
            view.setImageDrawable(error)
        } else {
            picasso.load(url).error(error).fit().centerInside().into(view)
        }

    }
}