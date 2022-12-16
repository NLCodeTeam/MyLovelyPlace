package ru.nlct.mylovelyplace.presentation.ui.home.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

class DecodeBitmapTask(
    private val resources: Resources,
    @DrawableRes private val bitmapResId: Int,
    private val reqWidth: Int,
    private val reqHeight: Int
) {

    private val cache: BackgroundBitmapCache = BackgroundBitmapCache.instance

    fun doInBackground(): Bitmap? {
        val cachedBitmap = cache.getBitmapFromBgMemCache(bitmapResId)
        if (cachedBitmap != null) {
            return cachedBitmap
        }
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, bitmapResId, options)
        val width = options.outWidth
        val height = options.outHeight
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfWidth = width / 2
            val halfHeight = height / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        options.inSampleSize = inSampleSize
        options.inJustDecodeBounds = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        val decodedBitmap = BitmapFactory.decodeResource(resources, bitmapResId, options)
        cache.addBitmapToBgMemoryCache(bitmapResId, decodedBitmap)
        return decodedBitmap
    }
}