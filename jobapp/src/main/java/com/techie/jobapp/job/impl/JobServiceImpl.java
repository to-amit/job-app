package com.techie.jobapp.job.impl;

import com.techie.jobapp.job.Job;
import com.techie.jobapp.job.JobRepository;
import com.techie.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private  List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()){
            Job jobs = jobOptional.get();
            jobs.setTitle(updatedJob.getTitle());
            jobs.setDescription(updatedJob.getDescription());
            jobs.setMaxSalary(updatedJob.getMaxSalary());
            jobs.setMinSalary(updatedJob.getMinSalary());
            jobs.setLocation(updatedJob.getLocation());
            jobs.setCompany(updatedJob.getCompany());
            jobRepository.save(jobs);
            return true;
        }
        return false;
    }

    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJob(Long id) {
        return jobRepository.findById(id).orElse(null);

    }

    @Override
    public Boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
