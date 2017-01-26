package sec.project.controller;

import java.security.Principal;
import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sec.project.domain.Account;
import sec.project.repository.AccountRepo;
import sec.project.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountRepo repo;

    @Autowired
    private AccountService service;

    @Autowired
    private Principal p;

    @Autowired
    private EntityManagerFactory e;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccountAndRedirect(Model model) {
        return "redirect:/account/" + e.getPersistenceUnitUtil().getIdentifier(repo.findByUsername(p.getName())).toString();
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String getAccount(@PathVariable Long id, Model model) {
        Account acc = repo.findOne(id);
        model.addAttribute("account", acc);
        return "account";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGet(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        service.save(account);
        return "redirect:/login";
    }
}
