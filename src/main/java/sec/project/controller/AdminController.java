package sec.project.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sec.project.domain.Account;
import sec.project.repository.AccountRepo;

@Controller
public class AdminController {

    private AccountRepo repo;

    @Profile("heroku")
    @RequestMapping("/admin")
    public String adminMappingHeroku(Model model) throws URISyntaxException {
        List<Account> accounts = new ArrayList<>();
        if (!repo.findAll().isEmpty()) {
            accounts = repo.findAll();
        }
        model.addAttribute("account", accounts);
        return "admin";
    }
}
