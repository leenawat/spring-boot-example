package com.punyadev.batch.unit.writter;

import com.punyadev.batch.unit.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class UnitFlatFileItemWriter extends FlatFileItemWriter<Unit> {

    private static final Logger logger = LoggerFactory.getLogger(UnitFlatFileItemWriter.class);

    public UnitFlatFileItemWriter() {
        this.setResource(new FileSystemResource("units.txt"));
        this.setLineAggregator(new DelimitedLineAggregator<Unit>() {{
            setDelimiter(",");
            setFieldExtractor(unit -> new Object[]{
                    unit.getId(), unit.getName(), unit.getDescription()
            });
        }});
    }

    @Override
    public void write(Chunk<? extends Unit> items) throws Exception {
        logger.info("Writing items.size(): {}", items.size());
        super.write(items);
    }
}