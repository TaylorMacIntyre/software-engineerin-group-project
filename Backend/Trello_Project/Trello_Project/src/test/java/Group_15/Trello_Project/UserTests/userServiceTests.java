package Group_15.Trello_Project.UserTests;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.repository.UserRepository;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class userServiceTests {
    @Mock
    @Autowired
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImplementation userService;
    //private UserModel userModel;  DEMO says we don't need it
    String fName1 = "jane";
    String fName2 = "john";
    String lName = "doe";
    String email1 = "myEmail1";
    String email2 = "myEmail2";
    String password1 = "Password1!";
    String password2 = "Password2!";
    String answer1 = "ans1";
    String answer2 = "ans2";

    @BeforeEach
    public void setUp() throws EmailAlreadyRegisteredException {
        //unsure what to put here, or if it's even needed
    }
    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
    }



        //SIGNUP
    //check saved user is not null after successful signup
    @Test
    public void testSignUp_successfulSignUpUserNotNull() throws EmailAlreadyRegisteredException {
        UserModel user = new UserModel();
        user.setFirstName("jane");
        user.setLastName("doe");
        user.setEmail("email@provider");
        user.setPassword("secret");
        user.setSecurityAnswer("yellow");

        Mockito.when(userRepository.save(user)).thenReturn(user);
        HashMap<String,String> map = userService.signUpUser(user);

        assertEquals(map.get("result"), "1");
    }

    //Check EmailAlreadyRegisteredException is thrown when user signs up with email already in db
    @Test
    public void testSignUp_throwsEmailAlreadyRegisteredException() throws EmailAlreadyRegisteredException{
        UserModel userModel = new UserModel(fName1, lName, email1, password1, answer1);
        userService.signUpUser(userModel);
        assertThrows(EmailAlreadyRegisteredException.class, ()->userService.signUpUser(userModel));
    }

    //check -1 is returned when signup uses email already in DB
    @Test
    public void testSignUp_emailAlreadyPresentInDB() {
        //should return -1
    }



        //LOGIN
    @Test
    public void testLogin_successfulLogin() {
        //should return userModel ID
    }

    //check EmailNotRegisteredException is thrown when logging in user with wrong email
    @Test
    public void testLogin_throwsEmailNotRegisteredException() throws EmailNotRegisteredException {
        assertThrows(EmailNotRegisteredException.class, ()->userService.logInUser(email2, password1));
    }

    //check -1 is returned when input email is not in database
    @Test
    public void testLogin_incorrectEmail() {
        //should return -1
    }

    //check IncorrectPasswordException is thrown when logging in a user with an incorrect pw
    @Test
    public void testLogin_throwsIncorrectPasswordException() throws IncorrectPasswordException, EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel(fName1, lName, email1, password1, answer1);
        userService.signUpUser(userModel);
        assertThrows(IncorrectPasswordException.class, ()->userService.logInUser(email1, password2));
    }

    //check -1 is returned when user logs in with correct email, but incorrect password
    @Test
    public void testLogin_incorrectPassword() {
        //should return -1
    }




        //FORGOT PW
    //check that userModel.pw == new password after update pw is called
    @Test
    public void testForgotPW_successfulPasswordUpdate() {
        //ensure updated userModel password equals the new password input
    }

    //check that EmailNotRegisteredException is thrown when user tries to reset pw with an unregistered email
    @Test
    public void testForgotPW_throwsEmailNotRegisteredException() throws EmailNotRegisteredException {
        assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword(email1, answer1, password1));
    }

    //check that false is returned when user tries to reset pw with an email not registered
    @Test
    public void testForgotPW_emailNotInDB() {
        //check false is returned
    }

    //check IncorrectSecurityAnswerException is thrown when user tries to reset pw with wrong security Q answer
    @Test
    public void testForgotPW_throwsIncorrectSecurityAnswerException() throws IncorrectSecurityAnswerException, EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel(fName1, lName, email1, password1, answer1);
        userService.signUpUser(userModel);
        assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword(email1, answer2, password1));
    }

    //check that when user uses incorrect security answer to reset pw it returns false
    @Test
    public void testForgotPW_wrongSecurityAnswer() {
        //should return false
    }

    //check NewPasswordSameAsOldPasswordException is thrown when suer resets pw and new PW is same as old
    @Test
    public void testForgotPW_throwsNewPasswordSameAsOldPasswordException() throws NewPasswordSameAsOldPasswordException, EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel(fName1, lName, email1, password1, answer1);
        userService.signUpUser(userModel);
        assertThrows(NewPasswordSameAsOldPasswordException.class, ()->userService.updatePassword(email1, answer1, password1));
    }

    //check that false is returned when new pw == old pw
    @Test
    public void testForgotPW_newPasswordSameAsOld() {
        //returns false
    }
}
