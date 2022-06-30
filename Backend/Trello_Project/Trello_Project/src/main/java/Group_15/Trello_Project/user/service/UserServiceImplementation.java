package Group_15.Trello_Project.user.service;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;
    public HashMap<String, String> signUpUser(UserModel userModel) throws EmailAlreadyRegisteredException {
        try {
            Optional<UserModel> user = userRepository.findByEmail(userModel.getEmail());
            if(user.isPresent()){
                throw new EmailAlreadyRegisteredException();
            }
        } catch (EmailAlreadyRegisteredException emailExists) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("result", "-1");
            map.put("status", "Email Already Registered");
            return map;

        }
        userRepository.save(userModel);
        HashMap<String, String> map = new HashMap<String, String>();
        String result = userModel.getId().toString();
        map.put("result", result);
        map.put("status", "successful signup");
        return map;

    }

    public HashMap<String, String> logInUser(String email, String password) throws IncorrectPasswordException, EmailNotRegisteredException {
        Optional<UserModel> user;
        try{
            user = userRepository.findByEmail(email);
            if(user.isPresent()){
               UserModel userModel = user.get();
               if(password.equals(userModel.getPassword())){
                String result = userModel.getId().toString();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("result", result);
                map.put("status", "successful login");
                return map;

               } else {
                   throw new IncorrectPasswordException();
               }
            }
            else {
                throw new EmailNotRegisteredException();
            }
        } catch (EmailNotRegisteredException badEmail) {
            HashMap<String, String> map = new HashMap<String, String>();
            Integer error = -1;
            String result = error.toString();
            map.put("result", result);
            map.put("status", "Email Not Registered");
            return map;

        } catch (IncorrectPasswordException badPW) {
            HashMap<String, String> map = new HashMap<String, String>();
            Integer error = -1;
            String result = error.toString();
            map.put("result", result);
            map.put("status", "Incorrect Password");
            return map;

        }
    }

    public HashMap<String, String> updatePassword(String email, String securityAnswer, String newPw) throws EmailNotRegisteredException, IncorrectSecurityAnswerException, NewPasswordSameAsOldPasswordException {
        Optional<UserModel> user;
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            user = userRepository.findByEmail(email);
            if(user.isPresent()){
                UserModel userModel = user.get();

                if(securityAnswer.equals(userModel.getSecurityAnswer())) {

                    if (!newPw.equals(userModel.getPassword())){
                        userModel.setPassword(newPw);
                        userRepository.save(userModel);
                        map.put("result", userModel.getId().toString());
                        map.put("status", "successful update Password");
                        return map;

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
            map.put("result", "false");
            map.put("status", "Email Not Registered");
            return map;

            //Not sure what else to do here
        } catch (IncorrectSecurityAnswerException badAnswer) {
            map.put("result", "false");
            map.put("status", "Incorrect Security Answer");
            return map;

            //rlly not sure what else to do
        } catch (NewPasswordSameAsOldPasswordException oldPwNewPwSame) {
            map.put("result", "false");
            map.put("status", "New Password Same As Old Password");
            return map;

        }
    }

    public UserModel findUserByID(Integer userId) {
        UserModel userModel = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);
        if(optionalUserModel.isPresent()){
            userModel = optionalUserModel.get();
        }
        return userModel;
    }





    //UNCOMMENT WHEN BACKENDS ARE CONNECTED
/*
    public boolean addWorkspaceToUser(Integer id, WorkspaceModel workspaceModel) {
        //check if user exists, assume workspace has already been error checked
        UserModel userModel;
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userModel = user.get();
            List<WorkspaceModel> workspaces = userModel.getWorkspaces();
            if(workspaces == null){
                workspaces = new List<WorkspaceModel>();
            }
            workspaces.add(workspaceModel);
            userModel.setWorkspaces(workspaces);
            userRepository.save(userModel);
            return true;
        }else{
            return false;
        }
        //if they do then add workspace to user list
    }

    public List<WorkspaceModel> getAllWorkspaces(Integer id) {
        //check if user exists..
        UserModel userModel = null;
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userModel = user.get();
            return userModel.getWorkspaces();
        }else{
            return null;
        }
        //then send List<WorkspaceModel>
    }

    public boolean deleteUserWorkspace(Integer id, WorkspaceModel workspaceModel) {
        //check if user exists
        UserModel userModel = null;
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userModel = user.get();
            List<WorkspaceModel> workspaces = userModel.getWorkspaces();
            if(workspaces == null){
                //can't delete a workspace from an empty list
                return false;
            }
            return workspaces.remove(workspaceModel);
        }
        // then remove workspace from List<WorkspaceModel>
        //user doesn't exist
        return false;
    }

    public boolean addBoardToUser(Integer id, BoardModel boardModel){
        //check if user exists, assume boardModel has already been error checked
        UserModel userModel = null;
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userModel = user.get();
            List<BoardModel> boards = userModel.getBoards();
            if(boards == null){
                boards = new List<BoardModel>();
            }
            boards.add(boardModel);
            userModel.setBoards(boards);
            userRepository.save(userModel);
            return true;
        }else{
            return false;
        }
        // then add board to List<BoardModel>
    }

    public List<BoardModel> getAllBoards(Integer id){
        //check if user exists
        UserModel userModel = null;
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userModel = user.get();
            return userModel.getBoards();
        }else{
            return null;
        }
        // then return board list
    }

    public boolean deleteUserBoard(Integer id, BoardModel boardModel) {
        //check if user exists
        UserModel userModel = null;
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent()){
            userModel = user.get();
            List<BoardModel> boards = userModel.getBoards();
            if(boards == null){
                //can't delete a board from an empty list of boards
                return false;
            }
            return boards.remove(boardModel);
        }
        //user isn't present, return false
        return false;
    }
*/
}
