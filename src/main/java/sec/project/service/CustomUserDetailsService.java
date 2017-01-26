package sec.project.service;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.db.Database;
import sec.project.domain.Account;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Database db;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = db.loadUser(username);
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
        } catch (SQLException | URISyntaxException ex) {
            Logger.getLogger(CustomUserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UsernameNotFoundException("No such user: " + username);
    }

}
