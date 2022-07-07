package ru.nlct.mylovelyplace.presentation.ui.home.adapters

import android.content.Context
import android.graphics.drawable.Drawable
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

class SliderCard(itemView: View, cont: Context) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.image)
    private val mCntx = cont
    fun setContent(@DrawableRes name: String?) {
        val imagePath = mCntx.assets.open("img/$name.jpg")
        val image = Drawable.createFromStream(imagePath, null)
        imageView.load(image) {
            crossfade(true)
            placeholder(R.drawable.elbrus)
        }
    }
}