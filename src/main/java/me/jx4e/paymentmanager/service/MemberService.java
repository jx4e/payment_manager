package me.jx4e.paymentmanager.service;

import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void addMember(Member member) {
        repository.save(member);
    }

    public void updateMember(Long id, Member updatedMember) {
        Optional<Member> existingOptional = repository.findById(id);
        if (existingOptional.isPresent()) {
            Member existing = existingOptional.get();
            existing.setName(updatedMember.getName());
            existing.setEmail(updatedMember.getEmail());
            repository.save(existing);
        } else {
            throw new IllegalArgumentException("Member with ID " + id + " not found.");
        }
    }

    public void removeMember(Member member) {
        repository.delete(member);
    }

    public void removeMemberById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Member with ID " + id + " not found.");
        }
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return repository.findById(id);
    }
}
