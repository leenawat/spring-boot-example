package com.punyadev.batch.myconfig.repository;

import com.punyadev.batch.myconfig.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Config, Long> {
    List<Config> findAll();
}
