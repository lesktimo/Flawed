package sec.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String defaultMapping() {
        return "index";
    }

    @RequestMapping("/info")
    public String infoMapping() {
        return "info";
    }

    @RequestMapping("/login")
    public String loginMapping() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signupMapping() {
        return "signup";
    }

    @RequestMapping("/nocando")
    public String nocandoMapping() {
        return "nocando";
    }
}
