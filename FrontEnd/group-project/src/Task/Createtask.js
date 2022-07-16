
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateTaskForm from './CreateTaskForm';

function CreateTask() {
    const history = useHistory();
    const BoardID = localStorage.getItem("boardID");
    function createTaskHandler(task) {
        fetch(`http://localhost:9000/task/saveTask/${BoardID}`, {
            method: 'POST',
            body: JSON.stringify(task),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/task/${BoardID}`));
    }
    
    return (
        <CreateTaskForm createTask={createTaskHandler} />
    );
}

export default CreateTask;