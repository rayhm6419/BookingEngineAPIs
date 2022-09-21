package com.laioffer.booking.repository;

import com.laioffer.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository; //对数据库增删改查  缺点：难debug
//By extending the JpaRepository, Spring can help provide some default implementations of common database operations
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}


