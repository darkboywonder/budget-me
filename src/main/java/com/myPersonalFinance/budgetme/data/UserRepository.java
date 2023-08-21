package com.myPersonalFinance.budgetme.data;

import com.myPersonalFinance.budgetme.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int Id);
};
