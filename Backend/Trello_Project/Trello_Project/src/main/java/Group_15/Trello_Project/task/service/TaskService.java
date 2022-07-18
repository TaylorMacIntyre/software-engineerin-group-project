package Group_15.Trello_Project.task.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.repository.TaskRepository;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    UserServiceImplementation userService;
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

    public List<TaskModel> getTaskWithDate(Integer board_id, String status, String dateFilter)
    {
        BoardModel boardModel = null;
        Optional<BoardModel> optionalBoardModel = Optional.ofNullable(boardService.findBoardById(board_id));
        List<TaskModel> tasksWithStatus = new ArrayList<>();
        LocalDate localCurrentDate = LocalDate.now();

        if(optionalBoardModel.isPresent()) {

            boardModel = optionalBoardModel.get();
            List<TaskModel> boardTasks = boardModel.getTasks();

            for(int i =0; i < boardTasks.size(); i++)
            {
                if (dateFilter.equals("Today")) {
                    //compare the day, month and year
                    int year = localCurrentDate.getYear();
                    int month = localCurrentDate.getMonthValue();
                    int day = localCurrentDate.getDayOfMonth();
                    
                    LocalDate boardTasksDate = boardTasks.get(i).getDate();
                    
                    if (boardTasks.get(i).getStatus().equals(status) && (( boardTasksDate.getYear() == year ) && ( boardTasksDate.getMonthValue() == month ) && ( boardTasksDate.getDayOfMonth() == day ))) {
                        tasksWithStatus.add(boardTasks.get(i));
                    }
                } else if (dateFilter.equals("Week")) {
                    //Citation: https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8
                    //compare if week numbers same
                    WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    int currentWeekNumber = localCurrentDate.get(weekFields.weekOfWeekBasedYear());

                    LocalDate boardTasksDate = boardTasks.get(i).getDate();
                    int boardTaskWeekNumber = boardTasksDate.get(weekFields.weekOfWeekBasedYear());

                    if (boardTasks.get(i).getStatus().equals(status) && (currentWeekNumber == boardTaskWeekNumber)){
                        tasksWithStatus.add(boardTasks.get(i));
                    }
                } else if(dateFilter.equals("Overdue")) {
                    //compare the day, month and year
                    int year = localCurrentDate.getYear();
                    int month = localCurrentDate.getMonthValue();
                    int day = localCurrentDate.getDayOfMonth();

                    LocalDate boardTasksDate = boardTasks.get(i).getDate();

                    if ((boardTasks.get(i).getStatus().equals(status) && !boardTasks.get(i).getStatus().equals("Done")) && (( boardTasksDate.getYear() <= year ) && ( boardTasksDate.getMonthValue() <= month ) && ( boardTasksDate.getDayOfMonth() < day ))) {
                        tasksWithStatus.add(boardTasks.get(i));
                    }
                }
            }


        }

        return tasksWithStatus;
    }



    public TaskModel updateStatus(Integer task_id, String status)
    {
        TaskModel taskModel = null;

        Optional<TaskModel> optionalTaskModel = taskRepository.findById(task_id);

        try {
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


    public TaskModel assignUserToTask(Integer task_id, String email, Integer workspace_id){
        TaskModel taskModel = null;
        boolean userInWorkspace = userService.isUserInWorkspace(email, workspace_id);
        Optional<TaskModel> task = taskRepository.findById(task_id);

        if(!userInWorkspace){
            return null;
            //ie user isn't in workspace.. don't add the task to their list
            //could also mean email isn't connected to a valid user
        }

        //else the user is present, and in the current workspace
        try {
            if(task.isPresent()){
                taskModel = task.get();
                userService.addTaskToUser(taskModel, email);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return taskModel;
    }

    public List<TaskModel> searchTask(Integer board_id, String searchValue, String status){

        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = Optional.ofNullable(boardService.findBoardById(board_id));
        List<TaskModel> tasksWithSearchFilter = new ArrayList<>();

        if(optionalBoardModel.isPresent()) {

            boardModel = optionalBoardModel.get();
            List<TaskModel> boardTasks = boardModel.getTasks();

            for(int i =0; i < boardTasks.size(); i++) {
                if ((boardTasks.get(i).getStatus().equals(status)) && (boardTasks.get(i).getName().toUpperCase().contains(searchValue.toUpperCase()))) {
                    tasksWithSearchFilter.add(boardTasks.get(i));
                }
            }

        }

        return tasksWithSearchFilter;
    }

    public String retrieveTaskAssignee(Integer task_id){
        Optional<Integer> id = taskRepository.findUserByTask(task_id);
        String userFullName = "";
        try {
            if (id.isPresent()) {
                Integer user_id = id.get();
                userFullName = userService.getFullName(user_id);
                return userFullName;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userFullName;
    }
}
