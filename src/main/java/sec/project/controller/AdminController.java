package sec.project.controller;

import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sec.project.repository.AccountRepo;

@Controller
public class AdminController {

    @Autowired
    private AccountRepo repo;

    @RequestMapping("/admin")
    public String adminMappingHeroku(Model model) throws URISyntaxException {
        model.addAttribute("accounts", repo.findAll());
        return "admin";
    }

    @RequestMapping("/dbresetfromadminbutton")
    public String resetDb() {
        repo.deleteAll();
        return "redirect:/admin";
    }
}
