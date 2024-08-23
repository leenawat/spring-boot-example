package com.punyadev.batch.myconfig.config;

import com.punyadev.batch.book.entity.Book;
import com.punyadev.batch.config.AppConfig;
import com.punyadev.batch.myconfig.entity.Config;
//import com.punyadev.batch.myconfig.reader.YourItemReader;
import com.punyadev.batch.myconfig.reader.YourItemReader;
import com.punyadev.batch.myconfig.repository.ConfigRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Log4j2
@Configuration
public class MyConfigJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ConfigRepository configRepository;
    private final AppConfig appConfig;
    private final YourItemReader yourItemReader;
    private final YourItemWriter yourItemWriter;

    @Value("${app.config.file-name}")
    private String fileName;

    public MyConfigJobConfiguration(JobRepository jobRepository, PlatformTransactionManager transactionManager, ConfigRepository configRepository, AppConfig appConfig
            , YourItemReader yourItemReader, YourItemWriter yourItemWriter
    ) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.configRepository = configRepository;
        this.appConfig = appConfig;
        this.yourItemReader = yourItemReader;
        this.yourItemWriter = yourItemWriter;
    }

    @Bean
    public Job importUserJob() {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .next(step2())
//                .next(step3())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // อ่านค่า config จาก DB
                    List<Config> configs = configRepository.findAll();
                    // เก็บค่า config ลงใน context
                    chunkContext.getStepContext().getStepExecution().getExecutionContext().put("configs", configs);
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<Book, Book>chunk(appConfig.getChunkSize(), transactionManager)
                .reader(yourReader())
                .writer(yourWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Book> yourReader() {
        System.out.println("your reader starting");
        // สร้าง reader ที่อ่านข้อมูลจาก DB โดยใช้ค่า config
//        String dbUrl = appConfig.getDbUrl();
//        return new YourItemReader(configs);
        return yourItemReader;
    }
//
    @Bean
    @StepScope
    public ItemWriter<Book> yourWriter(@Value("#{jobParameters['outputFile']}") String outputFile) {
        System.out.println("your writer starting " + outputFile);
        // สร้าง writer ที่เขียนข้อมูลลงไฟล์
//        return new YourItemWriter(fileName, configs);
        return yourItemWriter;
    }

    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    // อัปโหลดไฟล์ที่ได้จาก step2 โดยใช้ค่า config
                    List<Config> configs = (List<Config>) chunkContext.getStepContext().getStepExecution().getExecutionContext().get("configs");
                    // ใช้ configs เพื่ออัปโหลดไฟล์
//                    uploadFile(configs);
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    private void uploadFile(List<Config> configs) {
        // โค้ดสำหรับการอัปโหลดไฟล์ไปยัง server
        String url = configs.stream().filter(c -> c.getAppKey().equals("upload-url")).findFirst().get().getAppValue();
        // ทำการอัปโหลดไฟล์ที่นี่
    }
}
