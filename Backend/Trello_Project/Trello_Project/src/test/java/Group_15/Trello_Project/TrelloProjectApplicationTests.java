package Group_15.Trello_Project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import Group_15.Trello_Project.EmptyPasswordException;
import Group_15.Trello_Project.EmailAlreadyRegisteredException;
import Group_15.Trello_Project.SecurityQAnswerEmptyException;
import Group_15.Trello_Project.PasswordNotMatchCriteriaException;
import Group_15.Trello_Project.EmailNotRegisteredException;
import Group_15.Trello_Project.IncorrectPasswordException;
import Group_15.Trello_Project.NewPasswordSameAsOldPasswordException;
import Group_15.Trello_Project.IncorrectSecurityQuestionException;



@SpringBootTest
class TrelloProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	public void setUp(){

		@Autowired
		userRepository userRepository;

		@Autowired
		userService userService;

		userService.signUp("myEmail", "Password1!", "SecurityQAnswer");

	}
		//SIGNUP
	//Check email addy not already in db
	@Test
	public void testSignUp_emailNotAlreadyInDB() throws EmailAlreadyRegisteredException{
		String email = "myEmail";
		String password = "Password2!";
		String answer = "ans";
		userService.signUp(email, password, answer);

		assertThrows
	}

	//check pw matches criteria
	@Test()
	public void testSignUp_pwDoesNotMatchCriteria() throws PasswordNotMatchCriteriaException {
		String email = "email1";
		String invalidPassword = "pw";
		String answer = "ans";
		userService.signUp( email, invalidPassword, "ans");
	}

	//check security question not empty string
	@Test
	public void testSignUp_SecurityQAnswerIsEmpty(){

	}

		//LOGIN
	//check for valid email in repository(?)
	@Test
	public void testLogin_emailAddressPresentInDB(){

	}

	//check for password !empty SLASH check it matches info in user model
	@Test
	public void testLogin_PWNotEmpty(){

	}

		//FORGOT PW
	//check old pw matches info in db
	@Test
	public void testForgotPW_oldPWisCorrect(){

	}

	//check new pw matches criteria
	@Test
	public void testForgotPW_newPWMatchesCriteria(){

	}

	//check new pw != old pw
	@Test
	public void testForgotPW_newPWDoesNotEqualNewPW(){

	}

	//check user security question is correct
	@Test
	public void testForgotPW_userSecurityQIsCorrect(){

	}
}



