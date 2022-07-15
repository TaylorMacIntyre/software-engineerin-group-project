import { Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';

function CreateTaskForm(props) {
    const TaskNameRef = useRef();
    const TaskDateRef = useRef();
    const TaskStatusRef = useRef();

    function createTask(e) {
        e.preventDefault();
        const TaskName = TaskNameRef.current.value;
        const TaskDate = TaskDateRef.current.value;
        const TaskStatus = TaskStatusRef.current.value;

        const task = {
            name: TaskName,
            date: TaskDate,
            status: TaskStatus
        };

        props.createTask(task);
    };

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Create New Task</Typography>
            <form onSubmit={createTask}>
                <TextField
                    id='taskName'
                    placeholder='Task Name'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={TaskNameRef} />
                <TextField
                    id='taskDate'
                    placeholder='Task Date'
                    variant='outlined'
                    multiline
                    rows={4}
                    required
                    fullWidth
                    margin='dense'
                    inputRef={TaskDateRef} />
                <TextField
                    id='taskStatus'
                    placeholder='Task Status'
                    variant='outlined'
                    multiline
                    rows={4}
                    required
                    fullWidth
                    margin='dense'
                    inputRef={TaskStatusRef} />
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Create Task
                </Button>
            </form>
        </section>
    );
};

export default CreateTaskForm;