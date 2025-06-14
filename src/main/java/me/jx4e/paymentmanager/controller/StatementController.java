package me.jx4e.paymentmanager.controller;

import me.jx4e.paymentmanager.model.Expense;
import me.jx4e.paymentmanager.model.Member;
import me.jx4e.paymentmanager.model.Statement;
import me.jx4e.paymentmanager.service.ExpenseService;
import me.jx4e.paymentmanager.service.MemberService;
import me.jx4e.paymentmanager.service.MemberStatementService;
import me.jx4e.paymentmanager.service.StatementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/statements")
public class StatementController {
    private final StatementService statementService;
    private final MemberService memberService;
    private final MemberStatementService memberStatementService;
    private final ExpenseService expenseService;

    public StatementController(
            StatementService statementService,
            MemberService memberService,
            MemberStatementService memberStatementService,
            ExpenseService expenseService
    ) {
        this.statementService = statementService;
        this.memberService = memberService;
        this.memberStatementService = memberStatementService;
        this.expenseService = expenseService;
    }

    @GetMapping
    public String getStatements(Model model) {
        model.addAttribute("statements", statementService.getAllStatements());
        model.addAttribute("object", new Statement());
        return "statements/list";
    }

    @GetMapping("/{id}")
    public String editStatement(@PathVariable("id") Long id, Model model) {
        Optional<Statement> statement = statementService.getStatementById(id);

        if (statement.isPresent()) {
            model.addAttribute("statement", statement.get());
            model.addAttribute("memberStatements", statement.get().getMemberStatements());
            model.addAttribute("members", memberService.getAllMembers());
            model.addAttribute("expenseObject", new Expense());
            return "statements/detail";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/add")
    public String addStatement(@ModelAttribute("statement") Statement statement, RedirectAttributes redirectAttributes) {
        statementService.addStatement(statement);
        redirectAttributes.addFlashAttribute("success", "Statement added successfully!");
        return "redirect:/statements";
    }

    @PostMapping("/{id}/edit")
    public String editStatement(@PathVariable("id") Long id, @ModelAttribute Statement updatedStatement, RedirectAttributes redirectAttributes) {
        statementService.updateStatement(id, updatedStatement);
        redirectAttributes.addFlashAttribute("success", "Statement updated successfully!");
        return "redirect:/statements";
    }

    @PostMapping("/{id}/delete")
    public String deleteStatement(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        statementService.removeStatementById(id);
        redirectAttributes.addFlashAttribute("success", "Statement removed successfully!");
        return "redirect:/statements";
    }

    @PostMapping("/{id}/add/members")
    public String addMembersToStatement(@RequestParam("memberIds") List<Long> memberIds, @PathVariable("id") Long statementId) {
        for (Long memberId : memberIds) {
            memberStatementService.addMemberToStatement(memberId, statementId);
        }
        return "redirect:/statements/" + statementId;
    }

    @PostMapping("/{statementid}/add/expense")
    public String addExpenseToStatement(
            @PathVariable("statementid") Long statementId,
            @ModelAttribute("expense") Expense expense,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Statement> statement = statementService.getStatementById(statementId);
        if (statement.isPresent()) {
            expense.setStatement(statement.get());
            expenseService.addExpense(expense);

            redirectAttributes.addFlashAttribute("success", "Expense added successfully!");
        }

        return "redirect:/statements/" + statementId;
    }

    @PostMapping("/{id}/add/members/share")
    public String addMembersToStatement(
            @RequestParam("memberId") Long memberId,
            @RequestParam("expenseId") Long expenseId,
            @PathVariable("id") Long statementId
    ) {

        return "redirect:/statements/" + statementId;
    }
}