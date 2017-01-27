package sec.project.controller;

import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sec.project.repository.AccountRepository;

@Controller
public class AdminController {

    @Autowired
    private AccountRepository repo;

    @RequestMapping("/admin")
    public String adminMapping(Model model) throws URISyntaxException {
        model.addAttribute("accounts", repo.findAll());
        return "admin";
    }

    @RequestMapping("/dbresetfromadminbutton")
    public String resetDb() {
        repo.deleteAll();
        return "redirect:/admin";
    }
}
