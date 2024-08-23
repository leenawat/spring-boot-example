package com.punyadev.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;
@Log4j2
public class CustomItemReadListener<T> implements ItemReadListener<T> {

    @Override
    public void beforeRead() {
        log.info("Before reading item");
    }

    @Override
    public void afterRead(T item) {
        log.info("After reading item: " + item.toString());
    }

    @Override
    public void onReadError(Exception ex) {
        log.info("Error during item reading: " + ex.getMessage());
    }
}
