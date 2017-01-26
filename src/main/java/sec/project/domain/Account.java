package sec.project.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @NotNull
    @Length(min = 4, max = 25)
    private String username;

    @NotNull
    @Length(min = 4)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
