package ru.nlct.mylovelyplace.data.notifications

import com.google.firebase.firestore.DocumentSnapshot
import com.google.type.DateTime
import ru.nlct.mylovelyplace.data.Consts
import ru.nlct.mylovelyplace.data.datasource.RemoteDataSource
import ru.nlct.mylovelyplace.domain.notifications.Notification
import ru.nlct.mylovelyplace.domain.notifications.NotificationRepository

class NotificationRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : NotificationRepository {

    override suspend fun getNotificationByIdRemote(notificationId: String): Notification {
        val notificationDocument = remoteDataSource.getDocumentById(Consts.NOTIFICATIONS, notificationId)
        return documentSnapshotToNotification(notificationDocument)
    }

    override suspend fun addNotificationRemote(notification: Notification): String {
        val newNotificationMap = notificationToMap(notification)
        val notificationId = remoteDataSource.addDocument(Consts.NOTIFICATIONS, newNotificationMap)
        return notificationId
    }

    override suspend fun deleteNotificationRemote(notificationId: String) {
        remoteDataSource.deleteDocument(Consts.NOTIFICATIONS, notificationId)
    }

    override suspend fun updateNotificationRemote(notification: Notification) {
        val newNotificationMap = notificationToMap(notification)
        remoteDataSource.updateDocument(Consts.NOTIFICATIONS, notification.id, newNotificationMap)
    }

    override suspend fun getNotificationsRemote(startNotificationId: String, count: Long): List<Notification> {
        return remoteDataSource.getCollection(Consts.NOTIFICATIONS, startNotificationId, count)
            .map { documentSnapshot ->  documentSnapshotToNotification(documentSnapshot)}
    }

    private fun notificationToMap(notification: Notification): Map<String, Any> {
        return mapOf(
            Consts.NOTIFICATION_PLACEID to notification.placeId,
            Consts.NOTIFICATION_TITLE to notification.title,
            Consts.NOTIFICATION_CONTENT to notification.content,
            Consts.NOTIFICATION_CREATOR to notification.creator,
            Consts.NOTIFICATION_TIMESCHEDULE to notification.timeSchedule,
            Consts.NOTIFICATION_COUNTOFLIKES to notification.countOfLikes,
        )
    }

    private fun documentSnapshotToNotification(document: DocumentSnapshot): Notification {
        return Notification(
            document.id,
            document.data?.get(Consts.NOTIFICATION_PLACEID) as String,
            document.data?.get(Consts.NOTIFICATION_TITLE) as String,
            document.data?.get(Consts.NOTIFICATION_CONTENT) as String,
            document.data?.get(Consts.NOTIFICATION_CREATOR) as String,
            document.data?.get(Consts.NOTIFICATION_TIMESCHEDULE) as DateTime,
            document.data?.get(Consts.NOTIFICATION_COUNTOFLIKES) as Int,
        )
    }
}