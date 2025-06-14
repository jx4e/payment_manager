package me.jx4e.paymentmanager.repository;

import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.MemberStatement;
import me.jx4e.paymentmanager.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberStatementRepository extends JpaRepository<MemberStatement, Long> {
    List<MemberStatement> findAllByMember(Member member);
    List<MemberStatement> findAllByStatement(Statement statement);
}
