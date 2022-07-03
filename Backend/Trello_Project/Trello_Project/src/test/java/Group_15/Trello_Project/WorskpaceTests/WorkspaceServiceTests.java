package Group_15.Trello_Project.WorskpaceTests;

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
        int dummyUserId = 1;

        workspace.setWorkspace_name("Test Workspace Name");
        workspace.setWorkspace_description("This is Test Workspace Description");

        when(userService.addWorkspaceToUser(any(),any())).thenReturn(true);
        Mockito.when(workspaceRepository.save(workspace)).thenReturn(workspace);

        WorkspaceModel savedWorkspace = workspaceService.createWorkspace(workspace,1);

        assertNotNull(savedWorkspace);
    }


}
