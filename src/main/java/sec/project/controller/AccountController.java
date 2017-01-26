package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Account;
import sec.project.repository.AccountRepo;

@Controller
public class AccountController {

    @Autowired
    private AccountRepo repo;

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String getAccount(@PathVariable Long id, Model model) {
        Account acc = repo.findOne(id);
        model.addAttribute("account", acc);
        return "account";
    }
}
