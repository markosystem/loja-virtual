package com.capitani.brasilprev.lojavirtual.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class Abstract implements Serializable {
    private static final long serialVersionUID = -8769068017487193186L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    protected User userCreated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @JsonIgnore
    @ManyToOne
    protected User userUpdated;

    @JsonIgnore
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abstract that = (Abstract) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
