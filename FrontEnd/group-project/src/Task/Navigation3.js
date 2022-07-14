import React from 'react';
import { Box, AppBar, Toolbar, Typography } from '@mui/material';
import { Link } from 'react-router-dom';

function Navigation3() {
    const uid = localStorage.getItem("uid");
    const workspaceID = localStorage.getItem("active_workspace");
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Link to={'/boards/' + workspaceID} style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Go back to board
                        </Typography>

                    </Link>
                    <Link to='/create-Task' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Create Task
                        </Typography>
                    </Link>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

export default Navigation3;