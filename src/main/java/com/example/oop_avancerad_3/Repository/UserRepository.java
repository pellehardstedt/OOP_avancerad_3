package com.example.oop_avancerad_3.Repository;

import com.example.oop_avancerad_3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
