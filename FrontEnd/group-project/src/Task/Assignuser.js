import React from 'react';
import { useHistory } from 'react-router-dom';
import AssignTaskForm from './AssignTaskForm';

function AssignTask() {
    const BoardID = localStorage.getItem("boardID")
    const tID = localStorage.getItem("taskID")
    const history = useHistory();
    function assignTaskHandler(email){
        fetch(`http://localhost:9000/task/addUserToTask/${tID}?email=` + email, {
            method: 'GET',
            
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/task/${BoardID}`));
    }

    return(
        <AssignTaskForm AssignTask ={assignTaskHandler}/>
    );
}

export default AssignTask;