package phoenix.uniquizandroid.dto;


import java.util.Set;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String email;
    private Set<String> roles;
    private UserStatistics userStatistics;

    public UserDTO(String username, String password,
                   String name, String email, Set<String> roles, UserStatistics userStatistics){
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setEmail(email);
        this.setRoles(roles);
        this.setUserStatistics(userStatistics);
    }


    public UserDTO() {
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }

    public String credentials(){
        return getUsername() + ":" + password;
    }

}
