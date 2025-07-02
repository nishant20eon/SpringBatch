package com.eon.springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {
	@Autowired
    private  JobLauncher jobLauncher;
	@Autowired
    private  Job importProductJob;
	@Autowired
    private  Job clearProductJob;

    @GetMapping("/run-import")
    public String runImportJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // Unique job instance
                .toJobParameters();

        jobLauncher.run(importProductJob, params);
        return "📥 Import Job Triggered";
    }

    @GetMapping("/run-clear")
    public String runClearJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(clearProductJob, params);
        return "🧹 Clear Job Triggered";
    }
}


