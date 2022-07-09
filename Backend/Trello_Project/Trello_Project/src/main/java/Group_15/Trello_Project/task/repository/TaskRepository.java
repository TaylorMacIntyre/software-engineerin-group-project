package Group_15.Trello_Project.task.repository;

import javax.transaction.Transactional;

import Group_15.Trello_Project.task.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
        
    //Query get all tasks by Status
    @Query(value = "from TaskModel where status = :status and id = :id")
    public Optional<TaskModel> findByStatus(@Param(value = "status") String status, @Param(value = "id") Integer id);
        
    //Don't think these are needed
    //Query get all tasks by status B
    //Query get all tasks by status C
}
    
    
    

