package ru.nlct.mylovelyplace.presentation.ui.home.cardslider

import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
import java.lang.IllegalStateException
import java.security.InvalidParameterException
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.min

/**
 * Extended [LinearSnapHelper] that works <b>only</b> with [CardSliderLayoutManager].
 */
class CardSnapHelper: LinearSnapHelper() {

    private lateinit var recyclerView: RecyclerView

    /**
     * Attaches the [CardSnapHelper] to the provided RecyclerView, by calling
     * [RecyclerView.setOnFlingListener].
     * You can call this method with `null` to detach it from the current RecyclerView.
     *
     * @param recyclerView The RecyclerView instance to which you want to add this helper or
     * `null` if you want to remove SnapHelper from the current
     * RecyclerView.
     *
     * @throws IllegalArgumentException if there is already a [RecyclerView.OnFlingListener]
     * attached to the provided [RecyclerView].
     *
     * @throws InvalidParameterException if provided RecyclerView has LayoutManager which is not
     * instance of CardSliderLayoutManager
     */
    @Throws(IllegalStateException::class)
    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
        if (recyclerView != null && recyclerView.layoutManager !is CardSliderLayoutManager) {
            throw InvalidParameterException("LayoutManager must be instance of CardSliderLayoutManager")
        }
        this.recyclerView = recyclerView!!
    }

    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        val lm = layoutManager as CardSliderLayoutManager
        val itemCount = lm.itemCount
        if (itemCount == 0) return RecyclerView.NO_POSITION
        val vectorProvider = layoutManager as ScrollVectorProvider
        val vectorForEnd =
            vectorProvider.computeScrollVectorForPosition(itemCount - 1) ?: return RecyclerView.NO_POSITION
        val distance = calculateScrollDistance(velocityX, velocityY)[0]
        var deltaJump: Int
        deltaJump = if (distance > 0) {
            floor((distance / lm.cardWidth).toDouble()).toInt()
        } else {
            ceil((distance / lm.cardWidth).toDouble()).toInt()
        }
        val deltaSign = Integer.signum(deltaJump)
        deltaJump = deltaSign * min(3, abs(deltaJump))
        if (vectorForEnd.x < 0) {
            deltaJump = -deltaJump
        }
        if (deltaJump == 0) {
            return RecyclerView.NO_POSITION
        }
        val currentPosition = lm.getActiveCardPosition()
        if (currentPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION
        }
        var targetPos = currentPosition + deltaJump
        if (targetPos < 0 || targetPos >= itemCount) {
            targetPos = RecyclerView.NO_POSITION
        }
        return targetPos
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager) =
        (layoutManager as CardSliderLayoutManager).getTopView()

    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
        val lm = layoutManager as CardSliderLayoutManager
        val viewLeft = lm.getDecoratedLeft(targetView)
        val activeCardLeft = lm.activeCardLeft
        val activeCardCenter = lm.activeCardLeft + lm.cardWidth / 2
        val activeCardRight = lm.activeCardLeft + lm.cardWidth
        val out = intArrayOf(0, 0)
        if (viewLeft < activeCardCenter) {
            val targetPos = lm.getPosition(targetView)
            val activeCardPos = lm.getActiveCardPosition()
            if (targetPos != activeCardPos) {
                out[0] = -(activeCardPos - targetPos) * lm.cardWidth
            } else {
                out[0] = viewLeft - activeCardLeft
            }
        } else {
            out[0] = viewLeft - activeCardRight + 1
        }
        if (out[0] != 0) {
            recyclerView.smoothScrollBy(out[0], 0, AccelerateInterpolator())
        }
        return intArrayOf(0, 0)
    }

    override fun createSnapScroller(layoutManager: RecyclerView.LayoutManager): LinearSmoothScroller =
        (layoutManager as CardSliderLayoutManager).getSmoothScroller(recyclerView)
}