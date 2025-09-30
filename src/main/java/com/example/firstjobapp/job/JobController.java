package com.example.firstjobapp.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")   // works at 2 level ie, class leve (here) and method leve (below)
public class JobController {
    private JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>( jobService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobBy(id);
        if(job!=null){
            return new ResponseEntity<>(job,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("job not exist",HttpStatus.NOT_FOUND);
    }

    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)  // can also use this
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){
        boolean updated = jobService.updatedJob(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("job updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("No job with this Id",HttpStatus.NOT_FOUND);
    }

}


//GET /jobs : get all jobs
//GET /jobs/{id} : get specific job by ID

//POST /jobs : create new jobs (request body should contain the job details
//DELETE /JOBS/{id}: delete a specific job by id
// PUT /jobs/{id} : update a specific job by ID

/*
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1
* */
