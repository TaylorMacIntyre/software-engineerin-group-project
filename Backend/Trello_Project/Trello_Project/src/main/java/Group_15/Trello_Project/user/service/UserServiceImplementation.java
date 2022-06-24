package Group_15.Trello_Project.user.service;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;
    public Integer signUpUser(UserModel userModel) throws EmailAlreadyRegisteredException {
        try {
            Optional<UserModel> user = userRepository.findByEmail(userModel.getEmail());
            if(user.isPresent()){
                throw new EmailAlreadyRegisteredException();
            }
        } catch (EmailAlreadyRegisteredException emailExists) {
            return -1;
        }
        userRepository.save(userModel);
        return userModel.getId();
    }

    /*
    @Override
    public void updatePassword(String email, String securityAnswer, String newPw) {
        //idk why this is here
        userRepository.findByEmail(email);
    }
    */

    public Integer logInUser(String email, String password) throws IncorrectPasswordException, EmailNotRegisteredException {
        Optional<UserModel> user;
        try{
            user = userRepository.findByEmail(email);
            if(user.isPresent()){
               UserModel userModel = user.get();
               if(password.equals(userModel.getPassword())){
                   return userModel.getId();
               } else {
                   throw new IncorrectPasswordException();
               }
            }
            else {
                throw new EmailNotRegisteredException();
            }
        } catch (EmailNotRegisteredException badEmail) {
            return -1;
        } catch (IncorrectPasswordException badPW) {
            return -1;
        }
    }

    public boolean updatePassword(String email, String securityAnswer, String newPw) throws EmailNotRegisteredException, IncorrectSecurityAnswerException, NewPasswordSameAsOldPasswordException {
        Optional<UserModel> user;
        try {
            user = userRepository.findByEmail(email);
            if(user.isPresent()){
                UserModel userModel = user.get();

                if(securityAnswer.equals(userModel.getSecurityAnswer())) {

                    if (!newPw.equals(userModel.getPassword())){
                        userModel.setPassword(newPw);
                        userRepository.save(userModel);
                    } else {
                        throw new NewPasswordSameAsOldPasswordException();
                    }
                }
                else{
                    throw new IncorrectSecurityAnswerException();
                }
            }
            else {
                throw new EmailNotRegisteredException();
            }
        } catch (EmailNotRegisteredException badEmail) {
            return false;
            //Not sure what else to do here
        } catch (IncorrectSecurityAnswerException badAnswer) {
            return false;
            //rlly not sure what else to do
        } catch (NewPasswordSameAsOldPasswordException oldPwNewPwSame) {
            return false;
        }

        return true;
    }

    public UserModel findUserByID(Integer userId) {
        UserModel userModel = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);
        if(optionalUserModel.isPresent()){
            userModel = optionalUserModel.get();
        }
        return userModel;
    }

    /*
    public boolean addWorkspaceToUser(Integer id, WorkspaceModel workspaceModel) {
        //check if user exists, assume workspace has already been error checked
        //if they do then add it to user list
    }

    public List<WorkspaceModel> getAllWorkspaces(Integer id) {
        //check if user exists..
        //then send List<WorkspaceModel>
    }

    public boolean deleteUserWorkspace(Integer id, WorkspaceModel workspaceModel) {
        //check if user exists
        // then remove workspace from List<WorkspaceModel>
    }

    public boolean addBoardToUser(Integer id, BoardModel boardModel){
        //check if user exists, assume boardModel has already been error checked
        // then add board to List<BoardModel>
    }

    public boolean List<BoardModel> getAllBoards(Integer id){
        //check if user exists
        // then return board list
    }

    public boolean deleteUserBoard(Integer id, BoardModel boardModel) {
        //check if user exists
        // then remove board from list
    }
     */

}
