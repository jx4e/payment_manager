package me.jx4e.paymentmanager.model;

import me.jx4e.paymentmanager.model.party.TransactionParty;
import me.jx4e.paymentmanager.model.expense.Expense;

import java.util.List;

public class Invoice implements Printable {
    private final String title;
    private final TransactionParty transactionParty;
    private final List<Expense> expenses;

    public Invoice(String title, TransactionParty transactionParty, List<Expense> expenses) {
        this.title = title;
        this.transactionParty = transactionParty;
        this.expenses = expenses;
    }

    public Invoice(TransactionParty transactionParty, List<Expense> expenses) {
        this(null, transactionParty, expenses);
    }

    @Override
    public String print() {
        StringBuilder out = new StringBuilder();

        if (title == null) out.append("INVOICE").append('\n');
        else out.append("INVOICE - ").append(getTitle()).append('\n');
        out.append("ISSUED TO: ").append(transactionParty.getName()).append('\n');
        expenses.forEach(e -> {
            out.append(e.toString()).append('\n');
        });
        out.append("TOTAL: $").append(expenses.stream()
                .map(Expense::getValue)
                .reduce(Float::sum)
                .orElse(-1f)
        );

        return out.toString();
    }

    public String getTitle() {
        return title;
    }

    public TransactionParty getDebtor() {
        return transactionParty;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
