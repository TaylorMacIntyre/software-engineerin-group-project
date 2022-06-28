package Group_15.Trello_Project.workspace.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/saveWorkspace", consumes = "application/json", produces = "application/json")
    public WorkspaceModel createWorkspace(@RequestBody WorkspaceModel workspaceModel)
    {
        return workspaceService.createWorkspace(workspaceModel);
    }


//    //CONNECTION TO TAYLOR'S BACKEND
//    @PostMapping(path="/saveWorkspace", consumes = "application/json", produces = "application/json")
//    public WorkspaceModel createWorkspace(@PathVariable Integer user_id, @RequestBody WorkspaceModel workspaceModel)
//    {
//
//        return workspaceService.createWorkspace(user_id, workspaceModel);
//    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/assignBoard/{workspace_id}")
    public WorkspaceModel updateBoard(@PathVariable Integer workspace_id, @RequestParam Integer board_id)
    {
        return workspaceService.updateBoard(workspace_id, board_id);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllWorkspaces")
    public List<WorkspaceModel> getAllWorkspaces()
    {
        return workspaceService.getAllWorkspaces();
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
