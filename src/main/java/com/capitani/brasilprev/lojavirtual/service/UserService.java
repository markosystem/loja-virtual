package com.capitani.brasilprev.lojavirtual.service;

import com.capitani.brasilprev.lojavirtual.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> findById(long id);

    User save(User user);

}