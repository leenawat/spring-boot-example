package com.punyadev.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

@Log4j2
public class CustomStepExecutionListener extends StepExecutionListenerSupport {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Step is about to start for Step Name: " + stepExecution.getStepName());
    }
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Step has completed with status: " + stepExecution.getStatus());
        // You can customize the ExitStatus based on your logic
        return ExitStatus.COMPLETED;
    }
}
