package sec.project.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Component;
import sec.project.domain.Account;

@Component
public class Database {

    public void saveUser(String username, String password) throws SQLException, URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String dbUsername = dbUri.getUserInfo().split(":")[0];
        String dbPassword = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Account (username, password) VALUES (" + username + ", " + password + ");");

        stmt.close();
        connection.close();
    }

    public Account loadUser(String username) throws SQLException, URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String dbUsername = dbUri.getUserInfo().split(":")[0];
        String dbPassword = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT username, password FROM Account WHERE username = " + username + ";");

        String returnName = "";
        String returnWord = "";

        while (rs.next()) {
            returnName = rs.getString("username");
            returnWord = rs.getString("password");

        }

        stmt.close();
        connection.close();

        Account acc = new Account();
        acc.setUsername(returnName);
        acc.setPassword(returnWord);

        return acc;
    }
}
