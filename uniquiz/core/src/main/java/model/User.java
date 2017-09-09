package model;

import dto.UserDTO;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class User implements Serializable {

    public enum Roles{STUDENT, TEACHER}

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Column(unique = true)
    private String username;
    private String password;
    private String name;

    @Id
    private String email;

    private Set<Roles> roles;

    private UserStatistics userStatistics;

    public User() {
        this.roles = new HashSet<>();
        this.roles.add(Roles.STUDENT);
        this.userStatistics = new UserStatistics();
    }

    public User(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles = new HashSet<>();
        this.roles.add(Roles.STUDENT);
        this.userStatistics = new UserStatistics();
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
        final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            throw new IllegalStateException("Invalid E-mail");
        }
        this.email = email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
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
        Set<String> set = new HashSet<>();
        for(Roles role : roles){
            set.add(role.name());
        }

        return new UserDTO(username, password, name, email, set);
    }
}
