package Group_15.Trello_Project.workspace.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/workspace")
public class WorkspaceController
{

    @Autowired
    WorkspaceService workspaceService;

    @PostMapping(path="/saveWorkspace/{user_id}", consumes = "application/json", produces = "application/json")
    public WorkspaceModel createWorkspace(@RequestBody WorkspaceModel workspaceModel, @PathVariable Integer user_id)
    {
        return workspaceService.createWorkspace(workspaceModel, user_id);
    }

    @PutMapping(path="/addUserToWorkspace/{workspace_id}")
    public WorkspaceModel addUserToWorkspace(@PathVariable Integer workspace_id, @RequestParam Integer user_id)
    {

        return workspaceService.addUserToWorkspace(workspace_id, user_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/assignBoard/{workspace_id}")
    public WorkspaceModel updateBoard(@PathVariable Integer workspace_id, @RequestParam Integer board_id)
    {
        return workspaceService.updateBoard(workspace_id, board_id);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateWorkspaceDetails/{workspace_id}")
    public WorkspaceModel updateWorkspaceDetails(@PathVariable Integer workspace_id, @RequestParam String workspace_name, @RequestParam String workspace_description)
    {
        return workspaceService.updateWorkspaceDetails(workspace_id, workspace_name, workspace_description);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllWorkspaces/{user_id}")
    public List<WorkspaceModel> getAllWorkspaces(@PathVariable Integer user_id)
    {
        return workspaceService.getAllWorkspaces(user_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getWorkspaceBoards/{workspace_id}/{user_id}")
    public List<BoardModel> getWorkspaceBoards(@PathVariable Integer workspace_id, @PathVariable Integer user_id)
    {
        return workspaceService.getWorkspaceBoards(workspace_id, user_id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getWorkspace/{workspace_id}")
    public WorkspaceModel getWorkspace(@PathVariable Integer workspace_id)
    {
        return workspaceService.getWorkspace(workspace_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteWorkspace/{workspace_id}")
    public void deleteWorkspace(@PathVariable Integer workspace_id)
    {
        workspaceService.deleteWorkspace(workspace_id);
    }

}
