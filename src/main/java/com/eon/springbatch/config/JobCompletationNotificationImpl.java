package com.eon.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
@Component
public class JobCompletationNotificationImpl implements JobExecutionListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void beforeJob(JobExecution jobExecution) {
		logger.info("Job Started");
	}

	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus()==BatchStatus.COMPLETED) {
		logger.info("Job Ended");
		}
	}
}
