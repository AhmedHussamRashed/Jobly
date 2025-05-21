package com.example.jobly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<NotificationModel> notificationList;

    public NotificationAdapter(List<NotificationModel> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationModel notification = notificationList.get(position);
        holder.title.setText(notification.getTitle());
        holder.message.setText(notification.getMessage());

        String dateString = notification.getDate(); // يجب أن يكون التاريخ بصيغة yyyy-MM-dd HH:mm:ss
        if (dateString != null && !dateString.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date notificationDate = sdf.parse(dateString);
                if (notificationDate != null) {
                    String timeAgo = getTimeAgo(notificationDate);
                    holder.date.setText(timeAgo);
                    holder.date.setVisibility(View.VISIBLE);
                }
            } catch (ParseException e) {
                holder.date.setVisibility(View.GONE);
            }
        } else {
            holder.date.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, message, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notificationTitle);
            message = itemView.findViewById(R.id.notificationMessage);
            date = itemView.findViewById(R.id.notificationDate); // تأكد أن لديك TextView بهذا الـ ID في item_notification.xml
        }
    }

    private String getTimeAgo(Date pastDate) {
        long now = System.currentTimeMillis();
        long diff = now - pastDate.getTime();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long days = TimeUnit.MILLISECONDS.toDays(diff);

        if (seconds < 60) {
            return "منذ ثوانٍ";
        } else if (minutes < 60) {
            return "منذ " + minutes + " دقيقة";
        } else if (hours < 24) {
            return "منذ " + hours + " ساعة";
        } else {
            return "منذ " + days + " يوم";
        }
    }
}
