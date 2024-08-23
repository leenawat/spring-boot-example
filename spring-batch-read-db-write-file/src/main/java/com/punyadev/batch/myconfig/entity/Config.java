package com.punyadev.batch.myconfig.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name ="config")
public class Config implements Serializable {
    @Id
    private Long id;
    @Column(name = "app_key")
    private String appKey;
    @Column(name = "app_value")
    private String appValue;

    // Getters and Setters
}
