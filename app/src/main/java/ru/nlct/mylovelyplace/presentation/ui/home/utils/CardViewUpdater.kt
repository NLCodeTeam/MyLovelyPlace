package ru.nlct.mylovelyplace.presentation.ui.home.utils

import android.view.View
import androidx.cardview.widget.CardView
import ru.nlct.mylovelyplace.presentation.ui.home.cardslider.DefaultViewUpdater

/**
 * CardViewUpdater for [CardSliderLayoutManager]
 */
class CardViewUpdater : DefaultViewUpdater() {

    override fun updateView(view: View, position: Float) {
        super.updateView(view, position)
        val card = view as CardView
        val alphaView = card.getChildAt(1)
        val imageView = card.getChildAt(0)
        if (position < 0) {
            val alpha = view.alpha
            view.setAlpha(1f)
            alphaView.alpha = 0.9f - alpha
            imageView.alpha = 0.3f + alpha
        } else {
            alphaView.alpha = 0f
            imageView.alpha = 1f
        }
    }
}