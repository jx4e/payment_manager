package me.jx4e.paymentmanager.model.expense;

import me.jx4e.paymentmanager.model.party.TransactionParty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TotalExpense extends Expense {
    public TotalExpense(String name, String description, float value) {
        super(name, description, value);
    }

    public Map<TransactionParty, SubExpense> splitByProportions(Map<TransactionParty, Float> proportions) {
        Map<TransactionParty, SubExpense> expenseMap = new LinkedHashMap<>();

        proportions.forEach((tp, f) -> {
            expenseMap.put(tp, getSubExpenseByProportion(tp, f));
        });

        return expenseMap;
    }

    public Map<TransactionParty, SubExpense> splitByAmount(Map<TransactionParty, Float> amounts) {
        Map<TransactionParty, SubExpense> expenseMap = new LinkedHashMap<>();

        amounts.forEach((tp, f) -> {
            expenseMap.put(tp, getSubExpenseByValue(tp, f));
        });

        return expenseMap;
    }

    public SubExpense getSubExpenseByProportion(TransactionParty party, float proportion) {
        return new SubExpense(this, proportion * getValue());
    }

    public SubExpense getSubExpenseByValue(TransactionParty party, float value) {
        return new SubExpense(this, value);
    }
}
