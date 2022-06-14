package ru.nlct.mylovelyplace.data.datasource

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

internal open class RemoteDataSourceImpl : RemoteDataSource {

    private val db = Firebase.firestore
    private val dbStorage = Firebase.storage

    override suspend fun addDocument(
        collectionName: String,
        documentData: Map<String, Any>
    ): String {
        return db.collection(collectionName).add(documentData).await().id
    }

    override suspend fun updateDocument(
        collectionName: String,
        documentId: String,
        newDocumentData: Map<String, Any>
    ) {
        db.collection(collectionName).document(documentId).set(newDocumentData).await()
    }

    override suspend fun deleteDocument(collectionName: String, documentId: String) {
        db.collection(collectionName).document(documentId).delete().await()
    }

    override suspend fun getDocumentById(collectionName: String, documentId: String): DocumentSnapshot {
        return db.collection(collectionName).document(documentId).get().await()
    }

    override suspend fun getCollection(collectionName: String): List<DocumentSnapshot> {
        return db.collection(collectionName).get().await().documents
    }

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