package Group_15.Trello_Project.user.controller;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceInterface userServiceInterface;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public HashMap<String, Integer> signupUser(@RequestBody UserModel userModel) throws EmailAlreadyRegisteredException {
        Integer result = userServiceInterface.signUpUser(userModel);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("result", result);
        return map;
    }

    //login?
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public HashMap<String, Integer> loginUser(@RequestBody Map<String, String> json) throws IncorrectPasswordException, EmailNotRegisteredException {
        String email = json.get("email");
        String password = json.get("password");
        Integer result = userServiceInterface.logInUser(email, password);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("result", result);
        return map;
    }

    //forgot pw?
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updatePW")
    public HashMap<String, Boolean> updatePassword(@RequestBody Map<String, String> json) throws IncorrectSecurityAnswerException, EmailNotRegisteredException, NewPasswordSameAsOldPasswordException {
        String email = json.get("email");
        String securityQAnswer = json.get("securityAnswer");
        String newPassword = json.get("newPw");
        java.lang.Boolean result = userServiceInterface.updatePassword(email, securityQAnswer, newPassword);
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("result", result);
        return map;
        //return userServiceInterface.updatePassword(email, securityQAnswer, newPassword);
    }
}
