package me.jx4e.paymentmanager.component;

import com.github.javafaker.Faker;
import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MemberService memberService;
    private final Faker faker = new Faker();

    public DataInitializer(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Generate 10 random members
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setName(faker.name().fullName());
            member.setEmail(faker.internet().emailAddress());
            memberService.addMember(member);
        }
        System.out.println("Random test members generated!");
    }
}

