package me.jx4e.paymentmanager.model;

import me.jx4e.paymentmanager.model.party.Issuer;
import me.jx4e.paymentmanager.model.party.TransactionParty;
import me.jx4e.paymentmanager.model.expense.Expense;
import me.jx4e.paymentmanager.model.expense.SubExpense;
import me.jx4e.paymentmanager.model.expense.TotalExpense;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Statement {
    private final Issuer issuer;
    private final List<TotalExpense> totalExpenses;
    private final List<TransactionParty> transactionParties;
    private final Map<TotalExpense, Map<TransactionParty, SubExpense>> subExpenses;

    public Statement(Issuer issuer) {
        this.issuer = issuer;
        totalExpenses = new ArrayList<>();
        transactionParties = new ArrayList<>();
        subExpenses = new ConcurrentHashMap<>();

        transactionParties.add(issuer);
    }

    public void addTransactionParty(TransactionParty transactionParty) {
        transactionParties.add(transactionParty);
    }

    public void removeTransactionParty(TransactionParty transactionParty) {
        transactionParties.remove(transactionParty);
    }

    public void addTotalExpense(TotalExpense expense) {
        totalExpenses.add(expense);
    }

    public void removeTotalExpense(TotalExpense expense) {
        totalExpenses.remove(expense);
    }

    public void setProportionalExpenseBreakdown(TotalExpense expense,
                                                Map<TransactionParty, Float> proportions) {
        subExpenses.put(expense, expense.splitByProportions(proportions));
    }

    public void setAmountExpenseBreakdown(TotalExpense expense,
                                          Map<TransactionParty, Float> amounts) {
        subExpenses.put(expense, expense.splitByAmount(amounts));
    }

    public List<SubExpense> getSubExpenses(TransactionParty transactionParty) {
        List<SubExpense> out = new ArrayList<>();

        subExpenses.forEach((te, m) -> {
            if (!m.containsKey(transactionParty)) return;

            out.add(m.get(transactionParty));
        });

        return out;
    }

    public List<SubExpense> getUnpaidExpenses() {
        List<SubExpense> unpaid = new ArrayList<>();

        totalExpenses.forEach(e -> {
            float total = e.getValue();

            if (subExpenses.containsKey(e)) {
                total -= subExpenses.get(e).values().stream()
                        .map(Expense::getValue)
                        .reduce(Float::sum)
                        .orElse(0f);
            }

            unpaid.add(e.getSubExpenseByValue(issuer, total));
        });

        return unpaid;
    }

    public Invoice getIndividualInvoice(TransactionParty transactionParty) {
        List<SubExpense> subExpenses = getSubExpenses(transactionParty);
        return new Invoice(issuer, transactionParty, (List<Expense>) ((List<?>) subExpenses));
    }

    public Invoice getInvoice() {
        return new Invoice(issuer, issuer, (List<Expense>) ((List<?>) totalExpenses));
    }

    public Invoice getUnpaidInvoice() {
        return new Invoice("Unpaid Expenses", issuer, issuer, (List<Expense>) ((List<?>) getUnpaidExpenses()));
    }

    public List<TotalExpense> getTotalExpenses() {
        return totalExpenses;
    }

    public List<TransactionParty> getDebtors() {
        return transactionParties;
    }

    public Map<TotalExpense, Map<TransactionParty, SubExpense>> getSubExpenses() {
        return subExpenses;
    }
}
