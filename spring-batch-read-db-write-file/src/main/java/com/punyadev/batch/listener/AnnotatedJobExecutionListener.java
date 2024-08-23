package com.punyadev.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

@Log4j2
public class AnnotatedJobExecutionListener {
    @BeforeJob
    public void beforeJob() {
        log.info("Job is about to start using annotations");
    }
    @AfterJob
    public void afterJob() {
        log.info("Job has completed using annotations");
    }
}
