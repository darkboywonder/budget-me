package com.myPersonalFinance.budgetme.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.myPersonalFinance.budgetme.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

        User findByUsername(String username);

}

