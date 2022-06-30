import React from 'react';
import { Box, AppBar, Toolbar, Typography } from '@mui/material';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Link to='/boards' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Boards
                        </Typography>
                    </Link>
                    <Link to='/create-board' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Create Board
                        </Typography>
                    </Link>
                    <Link to='/delete-board' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Delete Board
                        </Typography>
                    </Link>
                    <Link to='/WorkSpace' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Go back to Workspace
                        </Typography>
                    </Link>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

export default Navigation;