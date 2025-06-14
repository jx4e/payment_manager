package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.Main;
import me.jx4e.paymentmanager.model.Invoice;
import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.expense.Expense;
import me.jx4e.paymentmanager.repository.MemberRepository;
import me.jx4e.paymentmanager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("sigma", new Member());
        return "members";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        memberService.addMember(member);
        redirectAttributes.addFlashAttribute("success", "Member added successfully!");
        return "redirect:/members";
    }
}
