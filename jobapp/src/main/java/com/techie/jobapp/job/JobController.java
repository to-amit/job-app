package com.techie.jobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("job added successfully", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
        Job job = jobService.findJob(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){

        Boolean deleteResponse  = jobService.deleteJob(id);
        if (!deleteResponse) {
            return new ResponseEntity<>("Invalid Id/Id not present", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Removed", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String>  UpdateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updateResponse = jobService.updateJob(id, updatedJob);
        if(updateResponse){
            return  new ResponseEntity<>("Job Updated Successfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
