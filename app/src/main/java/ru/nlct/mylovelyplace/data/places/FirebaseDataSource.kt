package ru.nlct.mylovelyplace.data.places

import com.google.firebase.firestore.DocumentSnapshot

/**
 * Удаленный источник (Firebase) мест
 *
 * @author Beslan Kambiev
 */

interface FirebaseDataSource {

    suspend fun getCollection(collectionName: String): List<DocumentSnapshot>

    suspend fun getDocument(collectionName: String, documentId: String): DocumentSnapshot
}