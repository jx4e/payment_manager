package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String getMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("sigma", new Member()); // For form binding
        return "members";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        memberService.addMember(member);
        redirectAttributes.addFlashAttribute("success", "Member added successfully!");
        return "redirect:/members";
    }

    @PostMapping("/{id}/edit")
    public String editMember(@PathVariable Long id, @ModelAttribute Member updatedMember, RedirectAttributes redirectAttributes) {
        memberService.updateMember(id, updatedMember); // Assuming you have updateMember logic
        redirectAttributes.addFlashAttribute("success", "Member updated successfully!");
        System.out.println(id);
        return "redirect:/members";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        memberService.removeMemberById(id);
        redirectAttributes.addFlashAttribute("success", "Member removed successfully!");
        return "redirect:/members";
    }
}