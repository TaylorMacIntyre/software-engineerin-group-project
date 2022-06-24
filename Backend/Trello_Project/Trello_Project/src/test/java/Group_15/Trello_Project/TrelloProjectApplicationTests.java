package Group_15.Trello_Project;

import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.repository.UserRepository;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;





@SpringBootTest
class TrelloProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	private UserRepository userRepository;
	private UserServiceImplementation userService;
	private UserModel userModel;
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
		userModel = new UserModel(fName1, lName, email1, password1, answer1);
		userService.signUpUser(userModel);
	}
	@AfterEach
	public void cleanUp(){
		userRepository.deleteAll();
	}
		//SIGNUP
	//Check email addy not already in db
	@Test
	public void testSignUp_emailAlreadyInDB() throws EmailAlreadyRegisteredException{
		assertThrows(EmailAlreadyRegisteredException.class, ()->userModel = new UserModel(fName2, lName, email1, password1, answer1));
	}


		//LOGIN
	//email doesnt exist
	@Test
	public void testLogin_emailAddressNotPresentInDB() throws EmailNotRegisteredException{
		assertThrows(EmailNotRegisteredException.class, ()->userService.logInUser(email2, password1));
	}

	//check input password matches password in user model
	@Test
	public void testLogin_PWNotCorrect() throws IncorrectPasswordException{
		assertThrows(IncorrectPasswordException.class, ()->userService.logInUser(email1, password2));
	}

		//FORGOT PW
	//check security question answer is correct
	@Test
	public void testForgotPW_securityQAnswerNotCorrect() throws IncorrectSecurityAnswerException {
		assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword(email1, answer2, password1));
	}

	//check new pw != old pw
	@Test
	public void testForgotPW_newPWDoesNotEqualOldPW() throws NewPasswordSameAsOldPasswordException {
		assertThrows(NewPasswordSameAsOldPasswordException.class, ()->userService.updatePassword(email1, answer1, password1));
	}
}



