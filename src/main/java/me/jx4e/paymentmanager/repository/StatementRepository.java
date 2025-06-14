package me.jx4e.paymentmanager.repository;

import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long> {
}
