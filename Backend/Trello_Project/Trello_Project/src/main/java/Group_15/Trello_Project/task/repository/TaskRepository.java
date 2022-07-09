package Group_15.Trello_Project.task.repository;

import javax.transaction.Transactional;

import Group_15.Trello_Project.task.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional

public interface TaskRepository extends JpaRepository<TaskModel, Integer> {

}
    
    
    

