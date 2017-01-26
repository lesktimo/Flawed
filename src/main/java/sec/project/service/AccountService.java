package sec.project.service;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accRepo;

    @PostConstruct
    public void init() {
        if (accRepo.findByUsername("testi") == null) {
            Account acc = new Account();
            acc.setUsername("testi");
            acc.setPassword("testi");
            accRepo.save(acc);
        }
    }

    public void save(Account acc) {
        acc.setUsername(acc.getUsername());
        acc.setPassword(acc.getPassword());
        accRepo.save(acc);
    }

    public Account findByUsername(String username) {
        return accRepo.findByUsername(username);
    }

}
