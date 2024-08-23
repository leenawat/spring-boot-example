package com.punyadev.batch.unit.service;

import com.punyadev.batch.unit.model.Unit;
import com.punyadev.batch.unit.reader.UnitItemReader;
import com.punyadev.batch.unit.repository.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    private static final Logger logger = LoggerFactory.getLogger(UnitService.class);

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> findAllUnits() {
        return unitRepository.findAll();
    }

    public List<Unit> findUnitsByPage(int page, int size) {
        logger.info("Attempting to read Unit from page: {}", page); // Log ก่อนการอ่าน
        Pageable pageable = PageRequest.of(page, size);
        return unitRepository.findAll(pageable).getContent();
    }
}

