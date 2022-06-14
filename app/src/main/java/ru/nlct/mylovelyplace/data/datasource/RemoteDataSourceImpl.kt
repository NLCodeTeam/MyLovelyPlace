package ru.nlct.mylovelyplace.data.datasource

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await


/**
 * RemoteDataSourceImpl
 * Реализация интерфейса [RemoteDataSource]
 *
 * @author Камбиев Б. М. 15.06.2022
 */
internal class RemoteDataSourceImpl : RemoteDataSource {

    private val db = Firebase.firestore
    private val dbStorage = Firebase.storage

    override suspend fun addDocument(
        collectionName: String,
        documentData: Map<String, Any>
    ): String {
        val newId = db.collection(collectionName)
            .add(documentData)
            .await()
            .id
        return newId
    }

    override suspend fun updateDocument(
        collectionName: String,
        documentId: String,
        newDocumentData: Map<String, Any>
    ) {
        db.collection(collectionName)
            .document(documentId)
            .set(newDocumentData)
            .await()
    }

    override suspend fun deleteDocument(collectionName: String, documentId: String) {
        db.collection(collectionName)
            .document(documentId)
            .delete()
            .await()
    }

    override suspend fun getDocumentById(collectionName: String, documentId: String): DocumentSnapshot {
        val place = db.collection(collectionName)
            .document(documentId)
            .get()
            .await()
        return place
    }

    override suspend fun getCollection(collectionName: String, startDocumentId: String, count: Long): List<DocumentSnapshot> {
        val placeList =
            if (startDocumentId.isBlank())
                db.collection(collectionName)
                    .limit(count)
                    .get()
                    .await()
            else db.collection(collectionName)
                .orderBy(FieldPath.documentId())
                .startAfter(startDocumentId)
                .limit(count)
                .get()
                .await()

        return placeList.documents
    }

    override suspend fun addFile(fileId: String, uri: Uri): Uri {
        with (dbStorage.reference) {
            child(fileId).putFile(uri).await()

            val taskUrl = child(fileId)
                .downloadUrl
                .await()
            return taskUrl
        }
    }

    override suspend fun deleteFile(fileId: String) {
        dbStorage.reference
            .child(fileId)
            .delete()
            .await()
    }

    override suspend fun updateDocumentField(
        collectionName: String,
        documentId: String,
        fieldId: String,
        newData: Any
    ) {
        db.collection(collectionName)
            .document(documentId)
            .update(fieldId, newData)
            .await()
    }
}