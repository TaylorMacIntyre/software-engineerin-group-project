package Group_15.Trello_Project.WorskpaceTests;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.repository.WorkspaceRepository;
import Group_15.Trello_Project.workspace.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {WorkspaceService.class})
@ExtendWith(SpringExtension.class)
public class WorkspaceServiceTests {

    @MockBean
    private WorkspaceRepository workspaceRepository;


    @Autowired
    private WorkspaceService workspaceService;

    @MockBean
    private UserServiceImplementation userService;

    @MockBean
    private BoardService boardService;

    @Test
    public void createWorkspaceTest() {

        WorkspaceModel workspace = new WorkspaceModel();
        int mockUserId = 1;

        workspace.setWorkspace_name("Test Workspace Name");
        workspace.setWorkspace_description("This is Test Workspace Description");

        when(userService.addWorkspaceToUser(any(),any())).thenReturn(true);
        Mockito.when(workspaceRepository.save(workspace)).thenReturn(workspace);

        WorkspaceModel savedWorkspace = workspaceService.createWorkspace(workspace,mockUserId);

        assertNotNull(savedWorkspace);
    }

    @Test
    public void addUserToWorkspaceTest(){
        WorkspaceModel workspace = new WorkspaceModel();

        workspace.setWorkspace_name("Test Workspace Name");
        workspace.setWorkspace_description("This is Test Workspace Description");

        int mockUserId = 1;

        when(workspaceRepository.findById(any())).thenReturn(Optional.of(workspace));
        when(userService.addWorkspaceToUser(any(),any())).thenReturn(true);

        Mockito.when(workspaceRepository.save(workspace)).thenReturn(workspace);

        WorkspaceModel savedWorkspace = workspaceService.updateBoard(workspace.getId(),mockUserId);

        assertNotNull(savedWorkspace);

    }



    @Test
    public void updateBoardTest() {
        WorkspaceModel workspace = new WorkspaceModel();
        BoardModel board = new BoardModel();

        workspace.setWorkspace_name("Test Workspace Name");
        workspace.setWorkspace_description("This is Test Workspace Description");

        board.setBoard_name("This is Board Name");
        board.setBoard_description("This is Board Description");

        when(workspaceRepository.findById(any())).thenReturn(Optional.of(workspace));

        when(boardService.findBoardById(any())).thenReturn(board);

        Mockito.when(workspaceRepository.save(workspace)).thenReturn(workspace);

        WorkspaceModel savedWorkspace = workspaceService.updateBoard(workspace.getId(),board.getId());

        assertNotNull(savedWorkspace);
    }

    @Test
    public void getAllWorkspacesTest()
    {
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspace_name("Test Workspace Name");
        workspace.setWorkspace_description("This is Test Workspace Description");

        int mockUserId = 1;
        List<WorkspaceModel> userWorkspaces = new ArrayList<>();
        userWorkspaces.add(workspace);

        when(userService.getAllWorkspaces(any())).thenReturn(userWorkspaces);

        List<WorkspaceModel> resultList = workspaceService.getAllWorkspaces(mockUserId);

        assertNotNull(resultList);
    }

}

