package com.capitani.brasilprev.lojavirtual.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Abstract implements Serializable {
    private static final long serialVersionUID = -8769068017487193186L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    protected User userCreated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

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

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(User userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
