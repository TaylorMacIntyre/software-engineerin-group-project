package Group_15.Trello_Project.workspace.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    UserServiceImplementation userService;

    public WorkspaceModel createWorkspace(WorkspaceModel workspaceModel, Integer user_id)
    {

        WorkspaceModel workspace = workspaceRepository.save(workspaceModel);

        boolean success = userService.addWorkspaceToUser(user_id, workspace);

        if(success) {
            return workspace;
        }

        return null;
    }

    public WorkspaceModel addUserToWorkspace(Integer workspace_id, Integer user_id)
    {

        Optional<WorkspaceModel> optionalWorkspaceModel = workspaceRepository.findById(workspace_id);
        WorkspaceModel updatedWorkspaceModel =  null;
        boolean success = false;

        if(optionalWorkspaceModel.isPresent())
        {
            updatedWorkspaceModel = optionalWorkspaceModel.get();
            success = userService.addWorkspaceToUser(user_id, updatedWorkspaceModel);
        }

        if(success) {
            return updatedWorkspaceModel;
        }

        return null;

    }
    public WorkspaceModel updateBoard(Integer workspace_id, Integer board_id)
    {
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = null;
        try
        {
            workspace = workspaceRepository.findById(workspace_id);
            if(workspace.isPresent())
            {
                WorkspaceModel workspaceModel = workspace.get();
                BoardModel boardModel = boardService.findBoardById(board_id);

                List<BoardModel> boards = workspaceModel.getBoards();

                if(boards==null)
                {
                    boards = new ArrayList<>();
                }

                boards.add(boardModel);
                workspaceModel.setBoards(boards);

                updatedWorkspace = workspaceRepository.save(workspaceModel);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return updatedWorkspace;
    }

    public List<WorkspaceModel> getAllWorkspaces(Integer user_id)
    {
        List<WorkspaceModel> userWorkspaces = userService.getAllWorkspaces(user_id);
        return userWorkspaces;
    }

    public List<BoardModel> getWorkspaceBoards(Integer workspace_id, Integer user_id) {

        Optional<WorkspaceModel> workspace = null;
        List<BoardModel> workspaceBoards = new ArrayList<BoardModel>();
        List<BoardModel> userBoards = userService.getAllBoards(user_id);
        ArrayList<BoardModel> commonBoards = new ArrayList<>();

        try
        {
            workspace = workspaceRepository.findById(workspace_id);
            if(workspace.isPresent() && userBoards!=null)
            {
                WorkspaceModel workspaceModel = workspace.get();
                workspaceBoards = workspaceModel.getBoards();

                for(int i = 0 ; i < userBoards.size() ; i++)
                {
                    if(workspaceBoards.contains(userBoards.get(i)))
                    {
                        commonBoards.add(userBoards.get(i));
                    }
                }
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return commonBoards;
    }

    public boolean removeUserFromWorkspace(@PathVariable Integer workspace_id, @RequestParam Integer user_id)
    {
        Optional<WorkspaceModel> workspace = null;

        boolean success = false;

        workspace = workspaceRepository.findById(workspace_id);

        if(workspace.isPresent())
        {
            WorkspaceModel workspaceModel = workspace.get();

            success = userService.deleteUserWorkspace(user_id, workspaceModel);

        }

        return success;
    }

    public void deleteWorkspace(@PathVariable Integer workspace_id)
    {
        workspaceRepository.deleteById(workspace_id);
    }

    public WorkspaceModel getWorkspace(@PathVariable Integer workspace_id)
    {
        WorkspaceModel workspaceModel = null;

        Optional<WorkspaceModel> optionalWorkspaceModel = workspaceRepository.findById(workspace_id);

        if(optionalWorkspaceModel.isPresent())
        {
            workspaceModel = optionalWorkspaceModel.get();
        }

        return workspaceModel;

    }

    public WorkspaceModel updateWorkspaceDetails(Integer workspace_id, String workspace_name, String workspace_description)
    {
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = workspaceRepository.findById(workspace_id);

        try
        {
            workspace = workspaceRepository.findById(workspace_id);
            if(workspace.isPresent())
            {
                WorkspaceModel workspaceModel = workspace.get();

                if(workspace_name != null)
                {
                    workspaceModel.setWorkspace_name(workspace_name);
                }

                if(workspace_description != null)
                {
                    workspaceModel.setWorkspace_description(workspace_description);
                }

                updatedWorkspace = workspaceRepository.save(workspaceModel);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return updatedWorkspace;
    }


}
