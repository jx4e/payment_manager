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
}
