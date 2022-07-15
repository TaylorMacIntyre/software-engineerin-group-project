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
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

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

        try {
            if (optionalTaskModel.isPresent()) {
                taskModel = optionalTaskModel.get();
            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return taskModel;
    }

    public List<TaskModel> getTaskWithStatus(Integer board_id, String status)
    {
        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = Optional.ofNullable(boardService.findBoardById(board_id));
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

//    public List<TaskModel> getTaskWithDate(Integer board_id, String status, String date)
//    {
//        BoardModel boardModel = null;
//
//        Optional<BoardModel> optionalBoardModel = Optional.ofNullable(boardService.findBoardById(board_id));
//        List<TaskModel> tasksWithStatus = new ArrayList<>();
//
//        //https://beginnersbook.com/2017/10/java-convert-localdate-to-date/
//        ZoneId defaultZoneId = ZoneId.systemDefault();
//        LocalDate localCurrentDate = LocalDate.now();
//        LocalDate currentDateObj = Date.from(localCurrentDate.atStartOfDay(defaultZoneId).toInstant());
//
//        if(optionalBoardModel.isPresent()) {
//
//            boardModel = optionalBoardModel.get();
//            List<TaskModel> boardTasks = boardModel.getTasks();
//
//            for(int i =0; i < boardTasks.size(); i++)
//            {
//                if(date.equals("Today")) {
//                    if (boardTasks.get(i).getStatus().equals(status) && (boardTasks.get(i).getDate().compareTo(currentDateObj) == 0)) {
//                        tasksWithStatus.add(boardTasks.get(i));
//                    }
//                }
//            }
//
//
//        }
//
//        return tasksWithStatus;
//
//    }



    public TaskModel updateStatus(Integer task_id, String status)
    {
        TaskModel taskModel = null;

        Optional<TaskModel> optionalTaskModel = taskRepository.findById(task_id);

        try {
            //change to try and catch //error checking
            if (optionalTaskModel.isPresent()) {
                taskModel = optionalTaskModel.get();
                taskModel.setStatus(status);
                taskRepository.save(taskModel);

            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return taskModel;
    }

}
