package com.expert.demo.dbswitcher.entity;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Instant created;
    protected Instant updated;

    protected boolean remove = false;

    @PrePersist
    public void prePersist() {
        if (this.created == null) {
            this.created = Instant.now();
        }
        this.updated = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = Instant.now();
    }
}
