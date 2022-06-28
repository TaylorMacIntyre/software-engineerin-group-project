package Group_15.Trello_Project.workspace.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController
{

    @Autowired
    WorkspaceService workspaceService;

    //CONNECTION TO TAYLOR'S BACKEND
    @PostMapping(path="/saveWorkspace", consumes = "application/json", produces = "application/json")
    public WorkspaceModel createWorkspace(@PathVariable Long user_id, @RequestBody WorkspaceModel workspaceModel)
    {

        return workspaceService.createWorkspace(user_id, workspaceModel);
    }

    @PutMapping("/assignBoard/{workspace_id}")
    public WorkspaceModel updateBoard(@PathVariable Long workspace_id, @RequestParam Long board_id)
    {
        return workspaceService.updateBoard(workspace_id, board_id);

    }

    @GetMapping("/getAllWorkspaces")
    public List<WorkspaceModel> getAllWorkspaces()
    {
        return workspaceService.getAllWorkspaces();
    }


    @GetMapping("/getWorkspace/{workspace_id}")
    public WorkspaceModel getWorkspace(@PathVariable Long workspace_id)
    {
        return workspaceService.getWorkspace(workspace_id);
    }

    @DeleteMapping("/deleteWorkspace/{workspace_id}")
    public void deleteWorkspace(@PathVariable Long workspace_id)
    {
        workspaceService.deleteWorkspace(workspace_id);
    }

}
