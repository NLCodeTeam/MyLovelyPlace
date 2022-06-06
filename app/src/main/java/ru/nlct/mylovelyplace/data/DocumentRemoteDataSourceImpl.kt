package ru.nlct.mylovelyplace.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

internal class DocumentRemoteDataSourceImpl : DocumentRemoteDataSource {

    private val db = Firebase.firestore

    override suspend fun addDocument(
        collectionName: String,
        documentData: HashMap<String, Any>
    ): String {
        return try {
            val documentId = db.collection(collectionName).add(documentData).await().id
            documentId
        } catch (e: Exception) {
            ""
        }

    }

    override suspend fun updateDocument(
        collectionName: String,
        documentId: String,
        newDocumentData: HashMap<String, Any>
    ): Boolean {
        return try {
            db.collection(collectionName).document(documentId).set(newDocumentData).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteDocument(collectionName: String, documentId: String): Boolean {
        return try {
            db.collection(collectionName).document(documentId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getDocumentById(collectionName: String, documentId: String): Any {
        return db.collection(collectionName).document(documentId).get().await()
    }

    override suspend fun getCollection(collectionName: String): List<DocumentSnapshot> {
        return db.collection(collectionName).get().await().documents
    }
}