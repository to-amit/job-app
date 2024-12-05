package com.techie.jobapp.job;

import java.util.List;

public interface JobService {
    boolean updateJob(Long id, Job updatedJob);

    List<Job> findAll();
    void createJob(Job job);

    Job findJob(Long id);
    Boolean deleteJob(Long id);
}
