package ru.nlct.mylovelyplace.presentation.ui.home.cardslider

import android.view.View

/**
 * A ViewUpdater is invoked whenever a visible/attached card is scrolled.
 */
interface ViewUpdater {
    /**
     * Called when CardSliderLayoutManager initialized
     */
    fun onLayoutManagerInitialized(lm: CardSliderLayoutManager)

    /**
     * Called on view update (scroll, layout).
     * @param view      Updating view
     * @param position  Position of card relative to the current active card position of the layout manager.
     * 0 is active card. 1 is first right card, and -1 is first left (stacked) card.
     */
    fun updateView(view: View, position: Float)
}