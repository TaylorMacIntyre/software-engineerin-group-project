package Group_15.Trello_Project.user.service;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserServiceInterface {
    public HashMap<String, String> signUpUser(UserModel userModel) throws EmailAlreadyRegisteredException;

    public HashMap<String, String> updatePassword(String email, String securityAnswer, String newPw) throws EmailNotRegisteredException, IncorrectSecurityAnswerException, NewPasswordSameAsOldPasswordException;

    public HashMap<String, String> logInUser(String email, String password) throws IncorrectPasswordException, EmailNotRegisteredException;

    public UserModel findUserByID(Integer userId);

    public boolean addWorkspaceToUser(Integer id, WorkspaceModel workspaceModel);

    public boolean addBoardToUser(Integer id, BoardModel boardModel);

    public List<WorkspaceModel> getAllWorkspaces(Integer id);

    public List<BoardModel> getAllBoards(Integer id);

    /* UNCOMMENT WHEN BACKENDS ARE CONNECTED

    public boolean deleteUserWorkspace(Integer id, WorkspaceModel workspaceModel);

    public boolean deleteUserBoard(Integer id, BoardModel boardModel);
     */

}
