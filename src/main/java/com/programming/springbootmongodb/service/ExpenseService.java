package com.programming.springbootmongodb.service;

import com.programming.springbootmongodb.model.Expense;
import com.programming.springbootmongodb.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }
    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId()).
                orElseThrow(()-> new RuntimeException(
                String.format("cannot find expense by ID %s",expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(expense);

    }
    public List<Expense> getAllExpense(){
        return expenseRepository.findAll();
    }
    public Expense getExpensesByName(String name){
        return expenseRepository.findByName(name).
                orElseThrow(()-> new RuntimeException(
                String.format("cannot find expense by name %s",name)));

    }
    public void deleteExpenses(String id){
        expenseRepository.deleteById(id);
    }
}
