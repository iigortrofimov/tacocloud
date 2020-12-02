package com.trofimov.igor.tacos.repositories.springdata;

import com.trofimov.igor.tacos.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
