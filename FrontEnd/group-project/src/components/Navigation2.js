import React from 'react';
import { Box, AppBar, Toolbar, Typography } from '@mui/material';
import { Link } from 'react-router-dom';

function Navigation2() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Link to='/WorkSpace' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            WorkSpace
                        </Typography>

                    </Link>
                    <Link to='/create-WorkSpace' style={{ textDecoration: 'none', color: 'white' }}>
                        <Typography variant="h6" component="div" sx={{ padding: '0 8px' }}>
                            Create WorkSpace
                        </Typography>
                    </Link>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

export default Navigation2;