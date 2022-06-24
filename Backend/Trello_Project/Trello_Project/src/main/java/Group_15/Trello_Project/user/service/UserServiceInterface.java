package Group_15.Trello_Project.user.service;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.user.entity.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {
    public Integer signUpUser(UserModel userModel) throws EmailAlreadyRegisteredException;

    public boolean updatePassword(String email, String securityAnswer, String newPw) throws EmailNotRegisteredException, IncorrectSecurityAnswerException, NewPasswordSameAsOldPasswordException;

    public Integer logInUser(String email, String password) throws IncorrectPasswordException, EmailNotRegisteredException;

    public UserModel findUserByID(Integer userId);

    /*
    public boolean addWorkspaceToUser(Integer id, WorkspaceModel workspaceModel);

    public List<WorkspaceModel> getAllWorkspaces(Integer id);

    public boolean deleteUserWorkspace(Integer id, WorkspaceModel workspaceModel);

    public boolean addBoardToUser(Integer id, BoardModel boardModel);

    public boolean List<BoardModel> getAllBoards(Integer id);

    public boolean deleteUserBoard(Integer id, BoardModel boardModel);
     */

}
