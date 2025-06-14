package me.jx4e.paymentmanager.service;

import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.MemberStatement;
import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.repository.MemberStatementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberStatementService {
    private final MemberStatementRepository memberStatementRepository;
    private final MemberService memberService;
    private final StatementService statementService;

    public MemberStatementService(MemberStatementRepository memberStatementRepository,
                                  MemberService memberService,
                                  StatementService statementService) {
        this.memberStatementRepository = memberStatementRepository;
        this.memberService = memberService;
        this.statementService = statementService;
    }

    public void addMemberToStatement(Long memberId, Long statementId) {
        Optional<Member> member = memberService.getMemberById(memberId);
        Optional<Statement> statement = statementService.getStatementById(statementId);

        if (member.isPresent() && statement.isPresent()) {
            List<MemberStatement> existing = getAllMemberStatementByStatement(statement.get());

            // No Dupes
            if (existing.stream()
                    .anyMatch(ms ->
                            ms.getMember().getId().equals(memberId))
            ) return;

            MemberStatement ms = new MemberStatement();
            ms.setMember(member.get());
            ms.setStatement(statement.get());

            memberStatementRepository.save(ms);
        }
    }

    public List<MemberStatement> getAllMemberStatementByMember(Member member) {
        return memberStatementRepository.findAllByMember(member);
    }

    public List<MemberStatement> getAllMemberStatementByStatement(Statement statement) {
        return memberStatementRepository.findAllByStatement(statement);
    }
}
