package com.capitani.brasilprev.lojavirtual.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Abstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedBy
    @ManyToOne
    protected User userCreated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @LastModifiedBy
    @ManyToOne
    protected User userUpdated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
