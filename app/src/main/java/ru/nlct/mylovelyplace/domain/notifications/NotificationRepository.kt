package ru.nlct.mylovelyplace.domain.notifications

interface NotificationRepository {

    suspend fun getNotificationByIdRemote(notificationId: String): Notification

    suspend fun addNotificationRemote(notification: Notification): String

    suspend fun deleteNotificationRemote(notificationId: String)

    suspend fun updateNotificationRemote(notification: Notification)

    suspend fun getNotificationsRemote(startNotificationId: String, count: Long): List<Notification>
}