package ru.nlct.mylovelyplace.data

import com.google.firebase.firestore.DocumentSnapshot

interface DocumentRemoteDataSource {
    suspend fun addDocument(collectionName: String, documentData: HashMap<String, Any>): String
    suspend fun updateDocument(collectionName:String, documentId: String, newDocumentData: HashMap<String, Any>)
    suspend fun deleteDocument(collectionName: String, documentId: String)
    suspend fun getDocumentById(collectionName: String, documentId: String): DocumentSnapshot
    suspend fun getCollection(collectionName: String): List<DocumentSnapshot>
}