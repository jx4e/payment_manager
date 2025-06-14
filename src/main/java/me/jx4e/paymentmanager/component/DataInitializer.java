package me.jx4e.paymentmanager.component;

import com.github.javafaker.Faker;
import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.service.MemberService;
import me.jx4e.paymentmanager.service.StatementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MemberService memberService;
    private final StatementService statementService;
    private final Faker faker = new Faker();

    public DataInitializer(
            MemberService memberService,
            StatementService statementService
    ) {
        this.memberService = memberService;
        this.statementService = statementService;
    }

    @Override
    public void run(String... args) throws Exception {
        generateMembers(25);
        generateStatements(50);
    }

    private void generateMembers(int n) {
        for (int i = 0; i < n; i++) {
            Member member = new Member();
            member.setName(faker.rickAndMorty().character());
            member.setEmail(faker.internet().emailAddress());
            memberService.addMember(member);
        }
        System.out.println("Random test members generated!");
    }

    private void generateStatements(int n) {
        for (int i = 0; i < n; i++) {
            Statement statement = new Statement();
            statement.setTitle(faker.job().title());
            statementService.addStatement(statement);
        }
        System.out.println("Random test statements generated!");
    }
}

