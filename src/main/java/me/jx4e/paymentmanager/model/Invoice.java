package me.jx4e.paymentmanager.model;

import j2html.tags.UnescapedText;
import me.jx4e.paymentmanager.model.party.Issuer;
import me.jx4e.paymentmanager.model.party.TransactionParty;
import me.jx4e.paymentmanager.model.expense.Expense;

import java.util.List;

import static j2html.TagCreator.*;

public class Invoice implements HtmlRenderable {
    private final String title;
    private final Issuer issuer;
    private final TransactionParty transactionParty;
    private final List<Expense> expenses;

    public Invoice(String title, Issuer issuer, TransactionParty transactionParty, List<Expense> expenses) {
        this.title = title;
        this.issuer = issuer;
        this.transactionParty = transactionParty;
        this.expenses = expenses;
    }

    public Invoice(Issuer issuer, TransactionParty transactionParty, List<Expense> expenses) {
        this(null, issuer, transactionParty, expenses);
    }

    @Override
    public String toString() {
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

    @Override
    public String generateHtml() {
        return html(
                head(title("Invoice")),
                body(
                        h1("Invoice"),
                        p("Issued To: "),
                        p(getParty().getName()),
                        p("Issued By: "),
                        p(getIssuer().getName()),
                        h2("Expenses"),
                        ul(expenses.stream()
                                        .map(expense -> rawHtml(expense.generateHtml()))
                                        .toArray(UnescapedText[]::new)),
                        p("Total: $" + expenses.stream()
                                .map(Expense::getValue)
                                .reduce(Float::sum)
                                .orElse(-1f))
                                .withStyle("display:flex; justify-content:right;")
                )
        ).render();
    }

    public String getTitle() {
        return title;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public TransactionParty getParty() {
        return transactionParty;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
