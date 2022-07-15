import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ViewTask from './Viewtask';

function Task(){
    const {id} = useParams();
    const url1 = `http://localhost:9000/task/getTaskWithStatus/${id}?status=TODO`
    const url2 = `http://localhost:9000/task/getTaskWithStatus/${id}?status=DOING`
    const url3 = `http://localhost:9000/task/getTaskWithStatus/${id}?status=DONE`

    const [taskData1, setTask1] = useState([]);
    const [taskData2, setTask2] = useState([]);
    const [taskData3, setTask3] = useState([]);

    useEffect(function () {
        fetch(url1,{method:'GET'})
        .then(response => response.json())
        .then(task => {
            setTask1(task);
        });

    },[url]);

    useEffect(function () {
        fetch(url2,{method:'GET'})
        .then(response => response.json())
        .then(task => {
            setTask2(task);
        });

    },[url]);

    useEffect(function () {
        fetch(url3,{method:'GET'})
        .then(response => response.json())
        .then(task => {
            setTask3(task);
        });

    },[url]);

    return 
    <section>
        <div>
            <ViewTask >
        </div>    
        <div>
            
        </div>   
        <div>
            
        </div>   
    </section>
}

export default Task;