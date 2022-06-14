package ru.nlct.mylovelyplace.data.datasource

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot

interface RemoteDataSource {
    suspend fun addDocument(collectionName: String, documentData: Map<String, Any>): String
    suspend fun updateDocument(collectionName:String, documentId: String, newDocumentData: Map<String, Any>)
    suspend fun deleteDocument(collectionName: String, documentId: String)
    suspend fun getDocumentById(collectionName: String, documentId: String): DocumentSnapshot
    suspend fun getCollection(collectionName: String): List<DocumentSnapshot>
    suspend fun addFile(fileId: String, uri: Uri): Uri
    suspend fun deleteFile(fileId: String)
}