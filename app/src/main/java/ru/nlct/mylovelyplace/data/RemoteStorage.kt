package ru.nlct.mylovelyplace.data

import android.net.Uri

interface RemoteStorage {
    suspend fun addFile(fileId: String, uri: Uri): Uri
    suspend fun deleteFile(fileId: String)
}