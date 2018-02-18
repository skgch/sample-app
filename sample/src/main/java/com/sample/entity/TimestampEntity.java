package com.sample.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class TimestampEntity {

    @Column(updatable=false)
    private Timestamp createdTime;

    @Column
    private Timestamp updatedTime;

    @PrePersist
    public void prePersist() {
        Timestamp ts = new Timestamp(new Date().getTime());
        createdTime = ts;
        updatedTime = ts;
    }

    @PreUpdate
    public void preUpdate() {
        updatedTime = new Timestamp(new Date().getTime());
    }
}
