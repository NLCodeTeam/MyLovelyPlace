package ru.nct.mylovelyplace.data.firebase

import com.google.firebase.firestore.DocumentSnapshot

interface FirebaseDataSource {

    suspend fun getCollection(collectionName: String): List<DocumentSnapshot>

    suspend fun getDocument(collectionName: String, documentId: String): DocumentSnapshot
}