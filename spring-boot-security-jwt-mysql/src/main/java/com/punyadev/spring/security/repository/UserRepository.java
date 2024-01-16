package com.punyadev.spring.security.repository;


import com.punyadev.spring.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}
