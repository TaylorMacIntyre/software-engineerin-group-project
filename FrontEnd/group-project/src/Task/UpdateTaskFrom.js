import React, { useRef } from 'react';
import { Button, TextField, Typography } from '@mui/material';
function UpdateTaskForm(props) {
    const StatusRef = useRef();
    function updateTask(e) {
        e.preventDefault();
        const status = StatusRef.current.value;
        props.UpdateTask(status);
    }

    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Update Task</Typography>
            <form onSubmit={updateTask}>
                <TextField
                    id='Status'
                    placeholder='Status'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={StatusRef} />
                
                <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px' }}>
                    Update Task
                </Button>
            </form>
        </section>
    );
}

export default UpdateTaskForm;