package com.punyadev.batch.myconfig.config;

import com.punyadev.batch.book.entity.Book;
import com.punyadev.batch.myconfig.entity.Config;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class YourItemWriter implements ItemWriter<Book> {


    @BeforeStep
    public void beforeStep() {
        System.out.println("before step before step");
        // เข้าถึง StepExecution
        StepContext stepContext = StepSynchronizationManager.getContext();
        System.out.println("stepContext != null => " + stepContext != null);
        if (stepContext != null) {
            StepExecution stepExecution = stepContext.getStepExecution();
            // ดึงค่าจาก ExecutionContext
            stepExecution.getExecutionContext().get("yourKey");
        }
    }

    @Override
    public void write(Chunk<? extends Book> chunk) throws Exception {

    }
}
