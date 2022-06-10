package ru.nlct.mylovelyplace.data

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

internal class RemoteStorageImpl : RemoteStorage{

    private val dbStorage = Firebase.storage

    override suspend fun addFile(fileId: String, uri: Uri): Uri {
        with (dbStorage.reference) {
            child(fileId).putFile(uri).await()
            return child(fileId).downloadUrl.await()
        }
    }

    override suspend fun deleteFile(fileId: String) {
        with(dbStorage.reference) {
            child(fileId).delete().await()
        }
    }
}