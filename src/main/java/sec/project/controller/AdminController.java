package sec.project.controller;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Profile("heroku")
    @RequestMapping("/admin")
    public String adminMappingHeroku(Model model) throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("url", dbUrl);
        
        return "admin";
    }
    
    
//    @RequestMapping("/admin")
//    public String adminMappingLocal(Model model) throws URISyntaxException {
//
//        model.addAttribute("username", "testi");
//        model.addAttribute("password", "testi");
//        model.addAttribute("url", "db");
//        
//        return "admin";
//    }

}
