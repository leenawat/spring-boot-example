package com.punyadev.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Log4j2
public class CustomChunkListener implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("Before chunk processing: " + chunkContext.getStepContext().getStepName());
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        log.info("After chunk processing: " + chunkContext.getStepContext().getStepName());
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        log.info("Error during chunk processing: " + chunkContext.getStepContext().getStepName());
    }
}
