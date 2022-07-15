import React from 'react';
import { useHistory } from 'react-router-dom';
import UpdateTaskForm from './UpdateTaskFrom';

function UpdateTask() {
    const BoardID = localStorage.getItem("boardID")
    const tID = localStorage.getItem("taskID")
    const history = useHistory();
    function updateTaskHandler(Status){
        fetch(`http://localhost:9000/task/updateStatus/${tID}?status=` + Status, {
            method: 'PUT',
            body: JSON.stringify(Status),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/task/${BoardID}`));
    }

    return(
        <UpdateTaskForm UpdateTask ={updateTaskHandler}/>
    );
}

export default UpdateTask;