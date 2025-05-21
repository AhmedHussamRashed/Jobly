package com.example.jobly;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<List<NotificationModel>> notifications;

    public LiveData<List<NotificationModel>> getNotifications() {
        if (notifications == null) {
            notifications = new MutableLiveData<>();
            loadNotifications();
        }
        return notifications;
    }

    // تحميل الإشعارات من مصدر بيانات (API أو قاعدة بيانات محلية)
    public void loadNotifications() {
        // بيانات وهمية للتوضيح
        List<NotificationModel> notificationList = new ArrayList<>();
        notificationList.add(new NotificationModel("تم قبول طلبك!", "شكراً لاستخدام Jobly."));
        notificationList.add(new NotificationModel("وظيفة جديدة مقترحة", "مناسبة لك بناءً على ملفك الشخصي."));
        notificationList.add(new NotificationModel("تذكير", "لا تنسَ تحديث معلوماتك."));

        // تحديث LiveData
        notifications.setValue(notificationList);
    }
}
