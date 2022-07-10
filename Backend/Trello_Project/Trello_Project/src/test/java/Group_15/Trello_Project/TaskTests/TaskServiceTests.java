package Group_15.Trello_Project.TaskTests;

import Group_15.Trello_Project.task.repository.TaskRepository;
import Group_15.Trello_Project.task.service.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {

    @Mock
    @Autowired
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService = new TaskService();


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void cleanUp(){
        taskRepository.deleteAll();
    }

        //createTasks
    //successful create

    //unsuccessful createTasks


        //findByTaskId
    //successful, task id exists

    //unsuccessful, task doesn't exist


        //getTask
    //successful

    //unsuccessful, board ID doesn't exist

    //unsuccessful, status doesn't exist

    //NOT IMPLEMENTED YET
    //change status of task

    //add member to task

}
