package Group_15.Trello_Project.task.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskModel createTask(TaskModel taskModel, Integer board_id) {

        TaskModel task = taskRepository.save(taskModel);

    }

    public TaskModel findTaskById(Integer task_id)
    {
        TaskModel taskModel = null;

        Optional<TaskModel> optionalTaskModel = taskRepository.findById(task_id);

        if(optionalTaskModel.isPresent())
        {
            taskModel = optionalTaskModel.get();
        }

        return taskModel;
    }

    public boolean deleteTask(@PathVariable Integer task_id)
    {
        taskRepository.deleteById(task_id);
        return true;
    }

    public TaskModel getTask(Integer board_id, String status)
    {
        TaskModel taskModel = null;
        Optional<TaskModel> task = null;

        Optional<TaskModel> optionalTaskModel = taskRepository.findById(task_id);

        if(optionalTaskModel.isPresent()) {

            taskModel = optionalTaskModel.get();
        }

        return taskModel;

    }
}
