package ru.nlct.mylovelyplace.presentation.ui.home.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.nlct.mylovelyplace.R

/**
 * Slider card - подгружает изображение в карточку
 *
 * @author Marianna Sabanchieva
 */

class SliderCard(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.image)
    fun setContent(name: String?) {
        val image = ASSETS_FOLDER.plus("$name.jpg")
        imageView.load(image) {
            crossfade(true)
            placeholder(R.drawable.elbrus)
        }
    }

    companion object{
        private const val ASSETS_FOLDER = "file:///android_asset/img/"
    }
}