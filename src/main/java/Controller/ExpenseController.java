package Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import data.ExpenseRepository;
import model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/expense")
public class ExpenseController {

@Autowired
private ExpenseRepository expenseRepository;

public static class ExpenseResponseObject {
    @JsonProperty
    private String added;
}

//    @GetMapping("/create")
//    public String displayCreateExpenseForm(Model model) {
//    model.addAttribute("title", "Create Expense");
//    model.addAttribute(new Expense());
//    return "expense/create";
//}

    @PostMapping(path = "/create", consumes = "application/json")
    public void processCreateExpenseForm(@RequestBody Expense expense) {
        System.out.println(expense.getName());
        expenseRepository.save(expense);

}
}
