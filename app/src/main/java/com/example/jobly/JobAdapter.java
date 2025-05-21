package com.example.jobly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<JobResponse.Job> jobList;

    // Constructor to initialize jobList
    public JobAdapter(List<JobResponse.Job> jobList) {
        this.jobList = jobList;
    }

    // Method to update the job list and notify adapter
    public void setJobList(List<JobResponse.Job> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged(); // Refresh the adapter when new data is set
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        // Check if the jobList is not null and the position is valid
        if (jobList != null && position >= 0 && position < jobList.size()) {
            JobResponse.Job job = jobList.get(position);
            holder.title.setText(job.getTitle());
            holder.company.setText(job.getCompany());
            holder.location.setText(job.getLocation());
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of the list or 0 if it's null
        return jobList != null ? jobList.size() : 0;
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        TextView title, company, location;

        public JobViewHolder(View itemView) {
            super(itemView);
            // Initialize the views
            title = itemView.findViewById(R.id.title);
            company = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
        }
    }
}
