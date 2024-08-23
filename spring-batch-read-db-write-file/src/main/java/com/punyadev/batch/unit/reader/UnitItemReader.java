package com.punyadev.batch.unit.reader;

import com.punyadev.batch.unit.model.Unit;
import com.punyadev.batch.unit.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class UnitItemReader implements ItemReader<Unit> {

    private final UnitService unitService;
    private int currentPage = 0;
    private Iterator<Unit> unitIterator;
    private static final int CHUNK_SIZE = 10; // กำหนดขนาดของข้อมูลที่ต้องการอ่านในแต่ละครั้ง

    public UnitItemReader(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    public Unit read() throws Exception {
        if (unitIterator == null || !unitIterator.hasNext()) {
            List<Unit> units = unitService.findUnitsByPage(currentPage, CHUNK_SIZE);
            if (units.isEmpty()) {
                return null; // No more data
            }
            unitIterator = units.iterator();
            currentPage++;
        }

        return unitIterator.hasNext() ? unitIterator.next() : null;
    }
}