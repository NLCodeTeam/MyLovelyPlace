package ru.nlct.mylovelyplace.data.places

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Реализация методов интерфейса FirebaseDataSource
 *
 * @author Beslan Kambiev
 */

class FirebaseDataSourceImpl(private val firebaseFirestore: FirebaseFirestore) : FirebaseDataSource {
    override suspend fun getCollection(collectionName: String): List<DocumentSnapshot> =
        firebaseFirestore.collection(collectionName).get().await().documents

    override suspend fun getDocument(collectionName: String, documentId: String): DocumentSnapshot =
        firebaseFirestore.collection(collectionName).document(documentId).get().await()
}