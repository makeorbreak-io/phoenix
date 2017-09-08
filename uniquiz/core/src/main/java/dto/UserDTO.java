package dto;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String email;

    public UserDTO(String username, String password,
                   String name, String email){
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setEmail(email);
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
}
