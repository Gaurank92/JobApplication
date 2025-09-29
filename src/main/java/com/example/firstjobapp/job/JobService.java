package com.example.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobBy(Long id);

    boolean deleteJobById(Long id);

    boolean updatedJob(Long id, Job updatedJob);
}
