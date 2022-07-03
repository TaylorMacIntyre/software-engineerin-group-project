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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


/*
    CITATION: Mockito.org
    Date Accessed:June 25th
    https://site.mockito.org/
 */

@ExtendWith(MockitoExtension.class)
public class userServiceTests {
    @Mock
    @Autowired
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImplementation userService = new UserServiceImplementation();
    //private UserModel userModel;  DEMO says we don't need it

    private HashMap<String, String> map = new HashMap<>();
    @BeforeEach
    public void setUp() throws EmailAlreadyRegisteredException {
        //unsure what to put here, or if it's even needed
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
        map.clear();
    }



    //SIGNUP
    //check saved user is not null after successful signup
    @Test
    public void testSignUp_successfulSignUpIDIsCorrect() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        when(userRepository.save(userModel)).thenReturn(userModel);
        map = userService.signUpUser(userModel);

        assertEquals(map.get("result"), "1");
    }

    //Check EmailAlreadyRegisteredException is thrown when user signs up with email already in db
    @Test
    public void testSignUp_throwsEmailAlreadyRegisteredException() throws EmailAlreadyRegisteredException{
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        map = userService.signUpUser(userModel);
        assertEquals("Email Already Registered", map.get("status"));
    }

    //check -1 is returned when signup uses email already in DB
    @Test
    public void testSignUp_emailAlreadyPresentInDB() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        map = userService.signUpUser(userModel);
        assertEquals("-1", map.get("result"));
    }



    //LOGIN
    @Test
    public void testLogin_successfulLogin() throws EmailAlreadyRegisteredException, IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("email@provider", "secret");
        assertEquals("1", map.get("result"));
    }

    //check EmailNotRegisteredException is thrown when logging in user with wrong email
    @Test
    public void testLogin_throwsEmailNotRegisteredException() throws EmailNotRegisteredException, IncorrectPasswordException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("student@dal", "yellow");
        assertEquals("Email Not Registered", map.get("status"));
        //assertThrows(EmailNotRegisteredException.class, ()->userService.logInUser("student@dal", "password1"));
    }

    //check -1 is returned when input email is not in database
    @Test
    public void testLogin_incorrectEmail() throws IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("student@dal", "yellow");
        assertEquals("-1", map.get("result"));
    }

    //check IncorrectPasswordException is thrown when logging in a user with an incorrect pw
    @Test
    public void testLogin_throwsIncorrectPasswordException() throws IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("email1", "wrongPassword");
        assertEquals("Incorrect Password", map.get("status"));
        //assertThrows(IncorrectPasswordException.class, ()->userService.logInUser("email1", "password2"));
    }

    //check -1 is returned when user logs in with correct email, but incorrect password
    @Test
    public void testLogin_incorrectPassword() throws IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("email1", "wrongPassword");
        assertEquals("-1", map.get("result"));
    }




    //FORGOT PW
    //check that userModel.pw == new password after update pw is called
    @Test
    public void testForgotPW_successfulPasswordUpdate() throws NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "answer1", "newPassword");
        assertEquals("newPassword", userModel.getPassword());
    }

    //check that EmailNotRegisteredException is thrown when user tries to reset pw with an unregistered email
    @Test
    public void testForgotPW_throwsEmailNotRegisteredException() throws EmailNotRegisteredException, NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email2", "answer1", "password1");
        assertEquals("Email Not Registered", map.get("status") );
        //assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword("email2", "answer1", "password1"));
    }

    //check that false is returned when user tries to reset pw with an email not registered
    @Test
    public void testForgotPW_emailNotInDB() throws NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email2", "answer1", "password1");
        assertEquals( "Email Not Registered", map.get("status"));
    }

    //check IncorrectSecurityAnswerException is thrown when user tries to reset pw with wrong security Q answer
    @Test
    public void testUpdatePW_throwsIncorrectSecurityAnswerException() throws IncorrectSecurityAnswerException, EmailAlreadyRegisteredException, NewPasswordSameAsOldPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "wrongAnswer", "newPassword");
        assertEquals("Incorrect Security Answer", map.get("status"));
        //assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword("email1", "answer2", "password1"));
    }

    //check that when user uses incorrect security answer to reset pw it returns false
    @Test
    public void testForgotPW_wrongSecurityAnswer() throws NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "wrongAnswer", "newPassword");
        assertEquals("false", map.get("result"));
    }

    //check NewPasswordSameAsOldPasswordException is thrown when suer resets pw and new PW is same as old
    @Test
    public void testForgotPW_throwsNewPasswordSameAsOldPasswordException() throws NewPasswordSameAsOldPasswordException, EmailAlreadyRegisteredException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "answer1", "password1");
        assertEquals("New Password Same As Old Password", map.get("status"));
        //assertThrows(NewPasswordSameAsOldPasswordException.class, ()->userService.updatePassword("email1", "answer1", "password1"));
    }

    //check that false is returned when new pw == old pw
    @Test
    public void testForgotPW_newPasswordSameAsOld() throws EmailAlreadyRegisteredException, NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "answer1", "password1");
        assertEquals("false", map.get("result"));
    }

    //find by user ID
    // 1 - userModel is returned when user is present
    @Test
    public void testFindByUserId_userIsPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        UserModel returnedUserModel = userService.findUserByID(1);
        assertNotNull(returnedUserModel);
    }

    //2 - null / empty userModel is returned when user isn't present
    @Test
    public void testFindById_userNotPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        UserModel returnedUserModel = userService.findUserByID(1);
        assertNull(returnedUserModel);
        //not sure if an empty object is null...
    }




/*
        //addWorkspaceToUser
    // check it is successful
    @Test
    public void testAddUserToWorkspace_successfulAdd() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUser(1, workspace);
        assertTrue(result);
    }


        //deleteUserWorkspace
    // check it is successful when workspace exists in user list
    @Test
    public void testDeleteUserWorkspace_success() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addWorkspaceToUser(1, workspace);
        boolean result = userService.deleteUserWorkspace(1, workspace);
        assertTrue(result);
    }

    //check it is not successful when workspace doesn't exist in user list
    @Test
    public void testDeleteUserWorkspace_failureWorkspaceNotInList() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.deleteUserWorkspace(1, workspace);
        assertFalse(result);
    }


        //getAllWorkspaces
    //check it is successful
    @Test
    public void testGetAllWorkspaces_Success() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("name", "description");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addWorkspaceToUser(1, workspace);
        List<WorkspaceModel> workspaces = userService.getAllWorkspaces(1);
        assertNotEquals(null, workspaces);
        //not sure why it isn't recognising assertNotNull, only in this test
    }


        //addBoardToUser
    //check it is successful
    @Test
    public void testAddBoardToUser_successful(){
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        BoardModel board = new BoardModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.addBoardToUser(1, board);
        assertTrue(result);
    }


        //deleteUserBoard
    //check it is successful when board exists in user list
    @Test
    public void testDeleteUserBoard_boardExistsSuccess() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        BoardModel board = new BoardModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addBoardToUser(1, board);
        boolean result = userService.deleteUserBoard(1, board);
        assertFalse(result);
    }

    //check it is not successful when board doesn't exist in user list
    @Test
    public void testDeleteUserBoard_boardDoesntExistFailure() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        BoardModel board = new BoardModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.deleteUserBoard(1, board);
        assertFalse(result);
    }


        //getAllBoards
    //check it is successful
    @Test
    public void testGetAllBoards_success() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        BoardModel board1 = new BoardModel("name1", "description1");
        BoardModel board2 = new BoardModel("name2", "description2");
        BoardModel board3 = new BoardModel("name3", "description3");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addBoardToUser(1, board1);
        List<BoardModel> workspace_board_list = new List<BoardModel>();
        workspace_board_list.add(board1);
        workspace_board_list.add(board2);
        workspace_board_list.add(board3);
        List<BoardModel> user_board_list = userService.getAllBoards(1, workspace_board_list);
        assertEquals(1, workspace_board_list.size());
    }


        //fullyDeleteWorkspace
    //ensure it returns true when workspace is removed
    @Test
    public void testFullyDeleteWorkspace_success() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace1 = new WorkspaceModel("name1", "description1");
        WorkspaceModel workspace2 = new WorkspaceModel("name2", "description2");
        BoardModel board1 = new BoardModel("name1", "description1");
        List<BoardModel> boards = new List<BoardModel>(){};
        boards.add(board1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addWorkspaceToUser(1, workspace1);
        userService.addWorkspaceToUser(1, workspace2);
        userService.addBoardToUser(1, board1);
        userService.fullyDeleteWorkspace(workspace1, boards);
        List<WorkspaceModel> user_workspace_list = userService.getAllWorkspaces(1);
        assertEquals(1, user_workspace_list.size());
    }

    //ensure it returns false when workspace isn't removed, ie it's not in list
    @Test
    public void testFullyDeleteWorkspace_failure() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace1 = new WorkspaceModel("name1", "description1");
        WorkspaceModel workspace2 = new WorkspaceModel("name2", "description2");
        BoardModel board1 = new BoardModel("name1b", "description1b");
        List<BoardModel> boards = new List<BoardModel>(){};
        boards.add(board1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addWorkspaceToUser(1, workspace1);
        assertFalse(userService.fullyDeleteWorkspace(workspace2, boards));
    }

        //fullyDeleteBoard
    //ensure it returns true when workspace is removed
    @Test
    public void testFullyDeleteBoard_success() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        BoardModel board1 = new BoardModel("name1", "description1");
        BoardModel board2 = new BoardModel("name2", "description2");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addBoardToUser(1, board1);
        userService.addBoardToUser(1, board2);
        assertTrue(userService.fullyDeleteBoard(board1));
    }

    //ensure it returns false when workspace isn't removed, ie it's not in list
    @Test
    public void testFullyDeleteBoard_failure() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        BoardModel board1 = new BoardModel("name1", "description1");
        BoardModel board2 = new BoardModel("name2", "description2");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addBoardToUser(1, board1);
        assertFalse(userService.fullyDeleteBoard(board2));
    }

*/
}
