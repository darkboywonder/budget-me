package com.myPersonalFinance.budgetme.data;

import com.myPersonalFinance.budgetme.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    List<Receipt> findByCategoryContaining(String tag);

}