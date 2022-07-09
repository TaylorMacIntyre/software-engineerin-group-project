package Group_15.Trello_Project.task.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/saveTask", consumes = "application/json", produces = "application/json")
    public TaskModel createTask(@RequestBody TaskModel taskModel, @RequestParam Integer board_id) {
        return taskService.createTask(taskModel, board_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteTask/{task_id}")
    public boolean deleteTask(@PathVariable Integer task_id)
    {
        return taskService.deleteTask(task_id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getTask/{board_id}")
    public TaskModel getTask(@PathVariable Integer board_id, @RequestParam String status)
    {
        return taskService.getTask(board_id, status);
    }


}
