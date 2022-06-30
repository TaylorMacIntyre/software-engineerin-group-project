package Group_15.Trello_Project.workspace.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
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

    public WorkspaceModel createWorkspace(WorkspaceModel workspaceModel)
    {
        return workspaceRepository.save(workspaceModel);

    }

//    //CONNECTION TO TAYLOR'S BACKEND
//    @Autowired
//    UserService userService;
//
//    public WorkspaceModel createWorkspace(Integer user_id, WorkspaceModel workspaceModel)
//    {
//        boolean success = userService.addWorkspaceToUser(user_id, workspaceModel);
//        WorkspaceModel workspace = null;
//        try
//        {
//            if(success) {
//                workspace = workspaceRepository.save(workspaceModel);
//            }
//        }
//        catch(Exception ex) {
//            ex.printStackTrace();
//        }
//        return workspace;
//
//    }


//    public WorkspaceModel addUser(Integer user_id, WorkspaceModel workspaceModel)
//    {
//        boolean success = userService.addWorkspaceToUser(user_id, workspaceModel);
//
//        Optional<WorkspaceModel> optionalWorkspaceModel = workspaceRepository.findById(workspaceModel.getId());
//        try
//        {
//            if(success) {
//                if(optionalWorkspaceModel.isPresent())
//                {
//                    workspaceModel = optionalWorkspaceModel.get();
//                }
//            }
//        }
//        catch(Exception ex) {
//            ex.printStackTrace();
//        }
//        return workspaceModel;
//
//    }
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

                if(boards!=null)
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

    public List<WorkspaceModel> getAllWorkspaces()
    {
        return workspaceRepository.findAll();
    }

    public List<BoardModel> getWorkspaceBoards(Integer workspace_id) {

        Optional<WorkspaceModel> workspace = null;
        List<BoardModel> boards = null;

        try
        {
            workspace = workspaceRepository.findById(workspace_id);
            if(workspace.isPresent())
            {
                WorkspaceModel workspaceModel = workspace.get();
                boards = workspaceModel.getBoards();
            }

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return boards;
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

}
