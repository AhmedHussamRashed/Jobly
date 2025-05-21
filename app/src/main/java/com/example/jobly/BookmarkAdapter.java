package com.example.jobly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private List<Job> jobList;
    private JobClickListener jobClickListener; // واجهة للاستماع للنقرات

    // Constructor لتوفير listener للتفاعل
    public BookmarkAdapter(List<Job> jobList, JobClickListener jobClickListener) {
        this.jobList = jobList;
        this.jobClickListener = jobClickListener;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_item, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Job job = jobList.get(position);

        // تعيين البيانات للعرض
        holder.jobTitle.setText(job.getTitle());
        holder.jobCompany.setText(job.getCompany());
        holder.jobLocation.setText(job.getLocation());

        // الاستماع للنقرات
        holder.itemView.setOnClickListener(v -> {
            if (jobClickListener != null) {
                jobClickListener.onJobClick(job); // تمرير الوظيفة للنقر
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList != null ? jobList.size() : 0; // إضافة تحقق من كون القائمة فارغة
    }

    // واجهة للاستماع إلى النقرات على الوظائف
    public interface JobClickListener {
        void onJobClick(Job job); // تمثل النقر على عنصر من العناصر
    }

    // ViewHolder للـ RecyclerView
    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle, jobCompany, jobLocation;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.jobTitle);
            jobCompany = itemView.findViewById(R.id.jobCompany);
            jobLocation = itemView.findViewById(R.id.jobLocation);
        }
    }
}
