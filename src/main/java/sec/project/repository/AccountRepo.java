package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
