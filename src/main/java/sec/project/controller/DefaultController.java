package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String defaultMapping() {
        return "index";
    }

    @RequestMapping("/info")
    public String infoMapping(HttpSession sess) {
        return "redirect:/" + sess.getId() + "/info";
    }

    @RequestMapping("/{secret}/info")
    public String infoMappingSession(@PathVariable String secret) {
        String sweetSweetSessionId = secret;
        return "info";
    }

    @RequestMapping("/login")
    public String loginMapping() {
        return "login";
    }

    @RequestMapping("/nocando")
    public String nocandoMapping() {
        return "nocando";
    }
}
