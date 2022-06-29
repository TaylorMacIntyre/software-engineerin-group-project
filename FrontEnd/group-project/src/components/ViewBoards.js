import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';

function ViewBoards(props) {
    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Boards</Typography>
            <Grid container spacing={2}>
                {props.boards.map((board) => {
                    return (
                        <Grid item xs={12} sm={12} md={4} lg={4} key={board.id}>
                            <Card elevation={6}>
                                <CardContent>
                                <Typography component='h5' variant='h5'>
                                    {board.id}
                                    </Typography>
                                    <Typography component='h4' variant='h4'>
                                    {board.board_name}
                                    </Typography>
                                    <Typography component='p' variant='p'>
                                    {board.board_description}
                                    </Typography>
                                    <Button variant='contained' sx={{ marginTop: '16px' }}>
                                        View All Tasks
                                    </Button>
                                </CardContent>
                            </Card>
                        </Grid>
                    );
                })}
            </Grid>
        </section>
    );
};

export default ViewBoards;