package me.jx4e.paymentmanager.service;

import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.repository.StatementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {
    private final StatementRepository repository;

    public StatementService(StatementRepository repository) {
        this.repository = repository;
    }

    public void addStatement(Statement statement) {
        repository.save(statement);
    }

    public void updateStatement(Long id, Statement updatedStatement) {
        Optional<Statement> existingOptional = repository.findById(id);
        if (existingOptional.isPresent()) {
            Statement existing = existingOptional.get();
            existing.setTitle(updatedStatement.getTitle());
            repository.save(existing);
        } else {
            throw new IllegalArgumentException("Statement with ID " + id + " not found.");
        }
    }

    public void removeStatement(Statement Statement) {
        repository.delete(Statement);
    }

    public void removeStatementById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Statement with ID " + id + " not found.");
        }
    }

    public List<Statement> getAllStatements() {
        return repository.findAll();
    }

    public Optional<Statement> getStatementById(Long id) {
        return repository.findById(id);
    }
}

