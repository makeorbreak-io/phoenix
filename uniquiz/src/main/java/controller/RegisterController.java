package controller;

import dto.UserDTO;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.UserRepository;

/**
 * Created by fabiolourenco on 09/09/17.
 */
@RestController
@RequestMapping("/signup")
public class RegisterController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> sendRequest(@RequestBody User newUser) {
        try {
            if(newUser.getEmail()!=null && newUser.getPassword()!=null&& newUser.getName()!=null&& newUser.getUsername()!=null) {

                UserRepository repo = new UserRepository();
                newUser = repo.save(newUser);

                return new ResponseEntity<>(newUser.toDTO(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
