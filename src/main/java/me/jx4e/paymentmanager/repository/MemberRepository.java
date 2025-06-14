package me.jx4e.paymentmanager.repository;

import me.jx4e.paymentmanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
