package ru.nlct.mylovelyplace.presentation.ui.home.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.nlct.mylovelyplace.R

/**
 * Slider card - подгружает изображение в карточку
 *
 * @author Marianna Sabanchieva
 */

class SliderCard(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.image)
    private val mctx = context
    fun setContent(@DrawableRes name: String?) {
        val imageID: Int = mctx.resources.getIdentifier(name, "drawable", mctx.packageName)
        imageView.load(imageID) {
            crossfade(true)
            placeholder(R.drawable.elbrus)
        }
    }
}