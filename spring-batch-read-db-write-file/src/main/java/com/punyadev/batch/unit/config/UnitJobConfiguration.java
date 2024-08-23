package com.punyadev.batch.unit.config;

import com.punyadev.batch.listener.CustomJobExecutionListener;
import com.punyadev.batch.unit.model.Unit;
import com.punyadev.batch.unit.reader.UnitItemReader;
import com.punyadev.batch.unit.tasklet.CustomTasklet2;
import com.punyadev.batch.unit.writter.UnitFlatFileItemWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Log4j2
//@Configuration
public class UnitJobConfiguration {

    private static final int CHUNK_SIZE = 10;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager batchTransactionManager;
    private final UnitItemReader unitItemReader;
    private final UnitFlatFileItemWriter unitFlatFileItemWriter;
    private final CustomTasklet2 customTasklet2;
    private final CustomJobExecutionListener customJobExecutionListener;

    public UnitJobConfiguration(
            JobRepository jobRepository,
            PlatformTransactionManager batchTransactionManager,
            UnitItemReader unitItemReader,
            UnitFlatFileItemWriter unitFlatFileItemWriter,
            CustomTasklet2 customTasklet2, CustomJobExecutionListener customJobExecutionListener) {
        this.jobRepository = jobRepository;
        this.batchTransactionManager = batchTransactionManager;
        this.unitItemReader = unitItemReader;
        this.unitFlatFileItemWriter = unitFlatFileItemWriter;
        this.customTasklet2 = customTasklet2;
        this.customJobExecutionListener = customJobExecutionListener;
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Unit, Unit>chunk(10, batchTransactionManager)
                .reader(unitItemReader)
//                .listener(new CustomChunkListener())
                .writer(unitFlatFileItemWriter)
//                .listener(new CustomChunkListener())
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet(customTasklet2, batchTransactionManager)
                .build();
    }

    @Bean
    public Step downloadStep() {
        return new StepBuilder("downloadStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        // โค้ดที่คุณต้องการให้ทำใน Tasklet นี้
                        log.info("Executing downloadStep...");
                        // ทำงานที่ต้องการที่นี่
                        return RepeatStatus.FINISHED;
                    }
                }, batchTransactionManager)
                .build();
    }

    @Bean
    public Step uploadStep() {
        return new StepBuilder("uploadStep", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        // โค้ดที่คุณต้องการให้ทำใน Tasklet นี้
                        log.info("Executing uploadStep...");
                        // ทำงานที่ต้องการที่นี่
                        return RepeatStatus.FINISHED;
                    }
                }, batchTransactionManager)
                .build();
    }

    @Bean
    public Job exportUnitJob() {
        return new JobBuilder("exportUnitJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(customJobExecutionListener)
                .start(downloadStep())
                .next(step1())
                .next(step2())
                .next(uploadStep())
                .build();
    }
}

