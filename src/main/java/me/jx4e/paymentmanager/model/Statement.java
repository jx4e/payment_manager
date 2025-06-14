package me.jx4e.paymentmanager.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberStatement> memberStatements;

    @OneToMany(mappedBy = "statement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;

    public Statement() {}

    public Statement(String title, String email) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public List<MemberStatement> getMemberStatements() {
        return memberStatements;
    }

    public void setMemberStatements(List<MemberStatement> memberStatements) {
        this.memberStatements = memberStatements;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalExpense() {
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", memberStatements=" + memberStatements +
                '}';
    }
}
