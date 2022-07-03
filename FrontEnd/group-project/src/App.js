

import React from "react";
import Navigation from "./components/Navigation"
import RegisterPage from "./pages/Register"
import LoginPage from "./pages/Login"
import HomePage from "./pages/Home"
import PasswordPage from "./pages/ForgotPassword"
import {Route, Switch} from "react-router-dom";

function App() {
  return (
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
        <Route path="/forgotpassword">
          <PasswordPage />

        </Route>
      </Switch>
    </div>
    
  );
}

export default App;
