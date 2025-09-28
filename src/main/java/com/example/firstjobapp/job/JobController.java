package com.example.firstjobapp.job;

import java.util.ArrayList;
import java.util.List;

public class JobController {
    private List<Job> jobs = new ArrayList<>();
    public List<Job> findAll(){
        return jobs;
    }

}
