import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { Container } from '@mui/material';
import Boards from './pages/Boards';
import CreateBoard from './pages/CreateBoard';
import Navigation from './components/Navigation';
import WorkSpace from './pages/Workspace';
import CreateWorkSpace from './pages/CreateWorkspace';
import Navigation2 from './components/Navigation2';
import DeleteBoard from './pages/DeleteBoard';
function App() {
  return (
    <React.Fragment>
      
      <Container>
        <Switch>
        <Route path={['/','/WorkSpace']} exact>
        <Navigation2 />
            <WorkSpace />
          </Route>

          <Route path='/create-WorkSpace' exact>
          <Navigation2 />
            <CreateWorkSpace />
          </Route>
          
          <Route path='/boards' exact>
          <Navigation />
            <Boards />
          </Route>
          <Route path='/create-board' exact>
          <Navigation />
            <CreateBoard />
          </Route>
          <Route path='/delete-board' exact>
          <Navigation />
            <DeleteBoard/>
          </Route>
        </Switch>
      </Container>
    </React.Fragment>
  );
};

export default App;
