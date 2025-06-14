package me.jx4e.paymentmanager.service;

import me.jx4e.paymentmanager.model.Expense;
import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }
}
