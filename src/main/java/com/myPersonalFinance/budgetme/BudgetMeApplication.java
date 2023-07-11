package com.myPersonalFinance.budgetme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class BudgetMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetMeApplication.class, args);
	}

}
