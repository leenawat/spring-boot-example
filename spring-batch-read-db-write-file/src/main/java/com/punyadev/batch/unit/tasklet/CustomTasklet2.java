package com.punyadev.batch.unit.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomTasklet2 implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(CustomTasklet2.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("This is first tasklet step");
        logger.info("SEC = {}", chunkContext.getStepContext().getStepExecutionContext());
        return RepeatStatus.FINISHED;
    }
}