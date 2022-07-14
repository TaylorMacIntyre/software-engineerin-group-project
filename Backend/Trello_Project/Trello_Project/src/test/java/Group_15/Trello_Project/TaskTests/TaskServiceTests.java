package Group_15.Trello_Project.TaskTests;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.repository.TaskRepository;
import Group_15.Trello_Project.task.service.TaskService;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {

    @Mock
    @Autowired
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService = new TaskService();

    @MockBean
    @Autowired
    BoardService boardService;

    @MockBean
    UserServiceImplementation userService;

    private TaskModel taskModel;
    private Calendar cal;
    private Date date1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //create date
        cal = Calendar.getInstance();
        cal.set(2022, 07, 20);
        date1 = cal.getTime();
        //create task model
        taskModel = new TaskModel("name1", date1, "To-Do");
    }

    @AfterEach
    public void cleanUp(){
        taskRepository.deleteAll();
    }

        //createTasks
    //successful create
    @Test
    public void createTasks_success(){
        //mock taskRepo.save
        Mockito.when(taskRepository.save(taskModel)).thenReturn(taskModel);
        TaskModel savedTask = taskService.createTask(taskModel, 1);
        //assert it returns a nonNull taskModel
        assertNotNull(savedTask);
    }



        //findByTaskId
    //successful, task id exists
    @Test
    public void findTaskById_successful(){
        Integer task_id = taskModel.getId();
        Optional<TaskModel> task = Optional.of(taskModel);
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(task);
        TaskModel returnedTask = taskService.findTaskById(task_id);
        assertNotNull(returnedTask);
    }

    //unsuccessful, task doesn't exist
    @Test
    public void findTaskById_Unsuccessful(){
        Integer task_id = taskModel.getId();
        Optional<TaskModel> task = Optional.empty();
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(task);
        TaskModel returnedTask = taskService.findTaskById(task_id);
        assertNull(returnedTask);
    }

        //getTaskWithStatus
    //successful
    @Test
    public void getTaskWithStatus_successful(){
        Integer board_id = 2;
        String status = "To-Do";
        BoardModel boardModel = new BoardModel();
        //Optional<BoardModel> board = Optional.of(boardModel);
        when(boardService.findBoardById(anyInt())).thenReturn(boardModel);
        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);
        List<TaskModel> returnedTasks = new ArrayList<>();
        assertNotNull(returnedTasks);
    }

    //unsuccessful, board ID doesn't exist
    @Test
    public void getTasksWithStatus_unsuccessful(){
        Integer board_id = 2;
        String status = "To-Do";
        BoardModel boardModel = null;
        //Optional<BoardModel> board = Optional.of(boardModel);
        when(boardService.findBoardById(anyInt())).thenReturn(boardModel);
        boardModel.setTasks(null);
        List<TaskModel> returnedTasks = new ArrayList<>();
        assertNull(returnedTasks);
    }

        //getTaskWithDate
    //successful
    @Test
    public void getTaskWithDate_success(){
        Integer board_id = 2;
        String status = "To-Do";
        Date date1 = taskModel.getDate();
        BoardModel tempBoard = new BoardModel();
        tempBoard.setId(board_id);
        boardService.addTaskToBoard(2, taskModel.getId());
        when(boardService.findBoardById(anyInt())).thenReturn(tempBoard);
        List<TaskModel> returnedTasks = taskService.getTaskWithDate(2, status, "Today");
        assertNotNull(returnedTasks);
    }

    //unsuccessful, board id doesn't exist
    @Test
    public void getTaskWithDate_failureBoardIdNonExistant(){
        BoardModel nullBoard = null;
        when(boardService.findBoardById(anyInt())).thenReturn(nullBoard);
        List<TaskModel> returnedTasks = taskService.getTaskWithDate(2, "Today", "02/04/2022");
        assertEquals(0, returnedTasks.size());
    }

        //updateStatus
    //successful, task id is present
    @Test
    public void updateStatusWithDate_success(){
        String newStatus = "Doing";
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(Optional.ofNullable(taskModel));
        TaskModel returnedTask = taskService.updateStatus(taskModel.getId(), newStatus);
        assertEquals(newStatus, returnedTask.getStatus());
    }

    //unsuccessful, task id isn't present
    @Test
    public void updateStatusWithDate_failureBadTaskId(){
        TaskModel nullTask = null;
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(Optional.empty());
        TaskModel returnedTask = taskService.updateStatus(10, "Doing");
        assertNull(returnedTask);
    }


    //NOT IMPLEMENTED YET
    //add member to task

}
