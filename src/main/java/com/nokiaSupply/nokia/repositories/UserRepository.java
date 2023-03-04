package com.nokiaSupply.nokia.repositories;

import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}