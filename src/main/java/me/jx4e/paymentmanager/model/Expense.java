package me.jx4e.paymentmanager.model;

import jakarta.persistence.*;
import me.jx4e.paymentmanager.service.ExpenseService;

import java.util.List;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double amount;
    private String currency;
    private String description;

    @ManyToOne
    private Statement statement;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseShare> expenseShares;

    public Expense() {}

    public Expense(String name, Double amount, String currency, String description) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public List<ExpenseShare> getExpenseShares() {
        return expenseShares;
    }

    public void setExpenseShares(List<ExpenseShare> expenseShares) {
        this.expenseShares = expenseShares;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", statement=" + statement +
                '}';
    }
}
