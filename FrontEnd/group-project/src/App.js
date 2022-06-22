import logo from './logo.svg';
import './App.css';
import React from "react";
import Navigation from "./components/Navigation"
import RegisterPage from "./pages/Register"
import LoginPage from "./pages/Login"
import HomePage from "./pages/Home"
import {Route, Switch} from "react-router-dom";

function App() {
  return (
    /*<div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>*/
    <div>
      <Navigation/>
      <Switch>
        <Route path="/register">
          <RegisterPage />
        </Route>
        <Route path={["/", "/login"]} exact>
          <LoginPage />

        </Route>
        <Route path="/home">
          <HomePage />

        </Route>
      </Switch>
    </div>
    
  );
}

export default App;
