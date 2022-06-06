package ru.nlct.mylovelyplace.data

import com.google.firebase.firestore.DocumentSnapshot

internal interface DocumentRemoteDataSource {
    suspend fun addDocument(collectionName: String, documentData: HashMap<String, Any>): String
    suspend fun updateDocument(collectionName:String, documentId: String, newDocumentData: HashMap<String, Any>): Boolean
    suspend fun deleteDocument(collectionName: String, documentId: String): Boolean
    suspend fun getDocumentById(collectionName: String, documentId: String): Any
    suspend fun getCollection(collectionName: String): List<DocumentSnapshot>
}