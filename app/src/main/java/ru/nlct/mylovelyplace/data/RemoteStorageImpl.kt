package ru.nlct.mylovelyplace.data

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

internal class RemoteStorageImpl : RemoteStorage{

    private val dbStorage = Firebase.storage

    override suspend fun addFile(fileReference: String, uri: Uri) {
        with (dbStorage.reference) {
            child(fileReference).putFile(uri).await()
        }
    }

    override suspend fun deleteFile(fileReference: String) {
        with(dbStorage.reference) {
            child(fileReference).delete().await()
        }
    }
}