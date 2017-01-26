package sec.project.service;

import java.util.Arrays;
import java.util.UUID;
import static java.util.UUID.randomUUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HttpSession sess;

    @Autowired
    private AccountRepo accRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

    @Bean
    public Cookie cookie() {
        String name = "flawedCookie";
        UUID value = randomUUID();
        Boolean useSecureCookie = new Boolean(false);
        int exp = 60 * 60 * 24;
        String cookiePath = "/";
        Cookie c = new Cookie(name, value.toString());
        c.setSecure(useSecureCookie);
        c.setMaxAge(exp);
        c.setPath(cookiePath);
        sess.setAttribute("cookie", c);
        return c;
    }
}
