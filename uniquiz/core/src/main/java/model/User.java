package model;

import dto.UserDTO;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class User implements Serializable {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    private String username;
    private String password;
    private String name;
    @Id
    private String email;

    public User() {
    }

    public User(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username != null ? username.equalsIgnoreCase(user.username) : false;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    public UserDTO toDTO(){
        return new UserDTO(username, password, name, email);
    }
}
