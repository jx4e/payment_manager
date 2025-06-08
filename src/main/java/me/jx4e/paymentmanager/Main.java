package me.jx4e.paymentmanager;

import me.jx4e.paymentmanager.model.Invoice;
import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.model.expense.Expense;
import me.jx4e.paymentmanager.model.party.Issuer;
import me.jx4e.paymentmanager.model.party.Recipient;
import me.jx4e.paymentmanager.model.party.TransactionParty;
import me.jx4e.paymentmanager.model.expense.TotalExpense;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Issuer issuer = new Issuer("UBC Swimming");
        Statement statement = new Statement(issuer);

        Recipient recipient1 = new Recipient("R1");
        Recipient recipient2 = new Recipient("R2");
        Recipient recipient3 = new Recipient("R3");
        statement.addTransactionParty(recipient1);
        statement.addTransactionParty(recipient2);
        statement.addTransactionParty(recipient3);

        TotalExpense expense = new TotalExpense(
                "Hotel",
                "Hotel",
                1000f
        );
        statement.addTotalExpense(expense);

        for (int i = 0; i < 10; i++) {
            statement.addTotalExpense(
                    new TotalExpense(
                            "Expense " + (i + 1),
                            "This is for bla bla bla",
                            100f * (i + 1)
                    )
            );
        }

        Map<TransactionParty, Float> proportionalCostBreakdown = new HashMap<>();
        proportionalCostBreakdown.put(recipient1, 0.2f);
        proportionalCostBreakdown.put(recipient2, 0.5f);
        proportionalCostBreakdown.put(recipient3, 0.5f);
        statement.setProportionalExpenseBreakdown(expense, proportionalCostBreakdown);

        Invoice totalInvoice = statement.getUnpaidInvoice();

        System.out.println(totalInvoice);

        System.out.println(totalInvoice.generateHtml());
    }

    public static Invoice getTemplate() {
        Issuer issuer = new Issuer("UBC Swimming");
        Statement statement = new Statement(issuer);

        Recipient recipient1 = new Recipient("R1");
        Recipient recipient2 = new Recipient("R2");
        Recipient recipient3 = new Recipient("R3");
        statement.addTransactionParty(recipient1);
        statement.addTransactionParty(recipient2);
        statement.addTransactionParty(recipient3);

        for (int i = 0; i < 10; i++) {
            TotalExpense expense = new TotalExpense(
                    "Expense " + (i + 1),
                    "This is for bla bla",
                    100f * (i + 1)
            );
            statement.addTotalExpense(expense);

            Map<TransactionParty, Float> proportionalCostBreakdown = new HashMap<>();
            proportionalCostBreakdown.put(recipient1, 0.2f);
            proportionalCostBreakdown.put(recipient2, 0.5f);
            proportionalCostBreakdown.put(recipient3, 0.5f);
            statement.setProportionalExpenseBreakdown(expense, proportionalCostBreakdown);
        }

        Invoice totalInvoice = statement.getIndividualInvoice(recipient1);

        return totalInvoice;
    }
}