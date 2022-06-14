package ru.nlct.mylovelyplace.data.datasource

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot


/**
 * RemoteDataSource
 * Интерфейс, описывающий методы источника данных для работы с базой данных Firebase
 *
 * @author Камбиев Б. М. 15.06.2022
 */
interface RemoteDataSource {
    /** Метод добавляет новый документ documentData в коллекцию collectionName.
     * @return Id документа */
    suspend fun addDocument(collectionName: String, documentData: Map<String, Any>): String

    /** Метод обновляет документ с documentId в коллекции collectionName данными newDocumentData. */
    suspend fun updateDocument(collectionName:String, documentId: String, newDocumentData: Map<String, Any>)

    /** Метод обновляет поле field документа с documentId в коллекции collectionName данными newData. */
    suspend fun updateDocumentField(collectionName:String, documentId: String, fieldId: String, newData: Any)

    /** Метод удаляет документ с documentId из коллекции collectionName. */
    suspend fun deleteDocument(collectionName: String, documentId: String)

    /** Метод возвращает документ с documentId из коллекцит collectionName.
     * @return DocumentSnapshot документ */
    suspend fun getDocumentById(collectionName: String, documentId: String): DocumentSnapshot

    /** Метод возвращает коллекцию документов collectionName, начиная с документа, который после документа со  startDocumentId, количество count.
     * @return List<DocumentSnapshot> список документов */
    suspend fun getCollection(collectionName: String, startDocumentId: String = "", count: Long = 10): List<DocumentSnapshot>

    /** Метод добавляет файл, переданный как Uri в базу и присваивает ему идентификатор fileId.
     * @return Uri документа, по которому можно его скачать */
    suspend fun addFile(fileId: String, uri: Uri): Uri

    /** Метод удаляет файл с идентификатором fileId из базы. */
    suspend fun deleteFile(fileId: String)
}