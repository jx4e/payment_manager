package me.jx4e.paymentmanager.service;

import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public void updateMember(Long id, Member updatedMember) {
        Optional<Member> existingOptional = memberRepository.findById(id);
        if (existingOptional.isPresent()) {
            Member existing = existingOptional.get();
            existing.setName(updatedMember.getName());
            existing.setEmail(updatedMember.getEmail());
            memberRepository.save(existing);
        } else {
            throw new IllegalArgumentException("Member with ID " + id + " not found.");
        }
    }

    public void removeMember(Member member) {
        memberRepository.delete(member);
    }

    public void removeMemberById(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Member with ID " + id + " not found.");
        }
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }
}
