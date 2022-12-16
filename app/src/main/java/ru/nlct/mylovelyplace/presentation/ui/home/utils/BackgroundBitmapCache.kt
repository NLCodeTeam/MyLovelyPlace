package ru.nlct.mylovelyplace.presentation.ui.home.utils

import android.graphics.Bitmap
import android.util.LruCache

/**
 * LruCache for caching background bitmaps for [DecodeBitmapTask].
 */
internal class BackgroundBitmapCache {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 5
    private val cacheSize = maxMemory / 5
    private val cache: LruCache<Int?, Bitmap> = object : LruCache<Int?, Bitmap>(cacheSize) {
        // The cache size will be measured in kilobytes rather than number of items.
        override fun sizeOf(key: Int?, bitmap: Bitmap) = bitmap.byteCount / 1024
    }

    fun addBitmapToBgMemoryCache(key: Int?, bitmap: Bitmap) {
        if (getBitmapFromBgMemCache(key) == null) {
            cache.put(key, bitmap)
        }
    }

    fun getBitmapFromBgMemCache(key: Int?): Bitmap? {
        return cache[key]
    }

    companion object {
        val instance = BackgroundBitmapCache()
    }
}