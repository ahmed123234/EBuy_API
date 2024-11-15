package com.example.simpleapi.Repositories;

import com.example.simpleapi.entities.User.User;
import com.example.simpleapi.entities.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByUsername(String username);

    Optional <User> findByUsername(String username);




    Optional<User> findByEmail(String email);

    @Query("select User from User where  id = ?1")
    Optional<User> findByUserId(Integer id);

    List<User> findAllByRole(UserRole role);

    @Query("select u from  User u where u.username like %?1%")
    List<User> searchCustomer(String pattern);
}
