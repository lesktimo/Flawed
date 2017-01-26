package sec.project.service;

import java.net.URISyntaxException;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sec.project.db.Database;
import sec.project.domain.Account;

@Service
public class AccountService {

    @Autowired
    private Database db;

    @PostConstruct
    public void init() throws SQLException, URISyntaxException {
        if (db.loadUser("testi") == null) {
            Account acc = new Account();
            acc.setUsername("testi");
            acc.setPassword("testi");
            db.saveUser(acc.getUsername(), acc.getPassword());
        }
    }

    public void save(Account acc) throws SQLException, URISyntaxException {
        acc.setUsername(acc.getUsername());
        acc.setPassword(acc.getPassword());
        db.saveUser(acc.getUsername(), acc.getPassword());
    }

    public Account findByUsername(String username) throws SQLException, URISyntaxException {
        return db.loadUser(username);
    }

}
