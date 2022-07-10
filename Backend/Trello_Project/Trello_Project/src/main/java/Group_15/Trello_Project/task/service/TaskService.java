package Group_15.Trello_Project.task.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    public TaskModel createTask(TaskModel taskModel, Integer board_id) {

        TaskModel task = taskRepository.save(taskModel);

        boolean success = boardService.addTaskToBoard(board_id, taskModel.getId());

        return task;

    }

    public TaskModel findTaskById(Integer task_id)
    {
        TaskModel taskModel = null;

        Optional<TaskModel> optionalTaskModel = taskRepository.findById(task_id);

        //change to try and catch //error checking
        if(optionalTaskModel.isPresent())
        {
            taskModel = optionalTaskModel.get();
        }

        return taskModel;
    }

    public List<TaskModel> getTaskWithStatus(Integer board_id, String status)
    {
        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);
        List<TaskModel> tasksWithStatus = new ArrayList<>();

        if(optionalBoardModel.isPresent()) {

            boardModel = optionalBoardModel.get();
            List<TaskModel> boardTasks = boardModel.getTasks();

            for(int i =0; i < boardTasks.size(); i++)
            {
                if(boardTasks.get(i).getStatus().equals(status))
                {
                    tasksWithStatus.add(boardTasks.get(i));
                }
            }


        }

        return tasksWithStatus;

    }

}
