package services;

import dto.UserDTO;
import model.User;
import repositories.UserRepository;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class UserService {

    public UserDTO createUser(String username, String password,
                              String name, String email){

        User user = new User(username, password, name, email);
        UserRepository repo = new UserRepository();
        return repo.save(user).toDTO();
    }

}
