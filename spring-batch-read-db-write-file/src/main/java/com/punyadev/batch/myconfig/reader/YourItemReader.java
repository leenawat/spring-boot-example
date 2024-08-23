package com.punyadev.batch.myconfig.reader;

import com.punyadev.batch.book.entity.Book;
import com.punyadev.batch.book.service.BookService;
import com.punyadev.batch.myconfig.entity.Config;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class YourItemReader implements ItemReader<Book> {

    private final BookService bookService;
    private final String category = "Fiction";
    private final int chunkSize = 10;

    private Iterator<Book> currentBatchIterator;
    private int currentPage = 0;

    @Autowired
    public YourItemReader(BookService bookService) {
        this.bookService = bookService;
//        this.category = "";
//        this.category = configs.stream()
//                .filter(config -> config.getKey().equals("book-category"))
//                .findFirst()
//                .get()
//                .getValue();
//        this.chunkSize = chunkSize;
//        loadNextBatch();
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("beforeStep beforeStep");
        // เข้าถึง StepExecutionContext
        StepContext stepContext = StepSynchronizationManager.getContext();
        if (stepContext != null) {
            // ดึงค่าจาก StepExecutionContext
//            this.dataList = (List<YourDataType>) stepContext.getStepExecution().getExecutionContext().get("yourKey");
        }
    }

    private void loadNextBatch() {
        // ดึงข้อมูลหนังสือตาม category และ page
        Page<Book> bookPage = bookService.getBooksByCategory(category, currentPage, chunkSize);
        currentBatchIterator = bookPage.getContent().iterator();

        // ถ้าไม่มีข้อมูลให้หยุด
        if (!currentBatchIterator.hasNext()) {
            currentBatchIterator = null; // หมายถึงไม่มีข้อมูลเพิ่มเติม
        } else {
            currentPage++; // เพิ่มหน้าเมื่อโหลดข้อมูลเสร็จ
        }
    }

    @Override
    public Book read() throws Exception {
        if (currentBatchIterator == null || !currentBatchIterator.hasNext()) {
            loadNextBatch();
            if (currentBatchIterator == null || !currentBatchIterator.hasNext()) {
                return null; // End of data
            }
        }
        return currentBatchIterator.next();
    }
}
