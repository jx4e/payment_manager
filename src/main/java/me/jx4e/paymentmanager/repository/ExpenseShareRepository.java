package me.jx4e.paymentmanager.repository;

import me.jx4e.paymentmanager.model.ExpenseShare;
import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.MemberStatement;
import me.jx4e.paymentmanager.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShare, Long> {
}
