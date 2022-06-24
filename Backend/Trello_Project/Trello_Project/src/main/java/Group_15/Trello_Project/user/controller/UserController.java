package Group_15.Trello_Project.user.controller;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceInterface userServiceInterface;

    @PostMapping("/save")
    public Integer signupUser(@RequestBody UserModel userModel) throws EmailAlreadyRegisteredException {
        return userServiceInterface.signUpUser(userModel);
    }

    //login?
    @GetMapping("/login/{email, password}")
    public Integer loginUser(@PathVariable String email, @PathVariable String password) throws IncorrectPasswordException, EmailNotRegisteredException {
        return userServiceInterface.logInUser(email, password);
    }

    //forgot pw?
    @PutMapping("/updatePW/{email, securityQAnswer, newPassword}")
    public boolean updatePassword(@PathVariable String email, @PathVariable String securityQAnswer, String newPassword) throws IncorrectSecurityAnswerException, EmailNotRegisteredException, NewPasswordSameAsOldPasswordException {
        return userServiceInterface.updatePassword(email, securityQAnswer, newPassword);
    }
}
