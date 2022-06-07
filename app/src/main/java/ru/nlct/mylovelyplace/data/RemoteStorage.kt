package ru.nlct.mylovelyplace.data

import android.net.Uri

internal interface RemoteStorage {
    suspend fun addFile(fileReference: String, uri: Uri)
    suspend fun deleteFile(fileReference: String)
}