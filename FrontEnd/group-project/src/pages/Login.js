import React, {useEffect, useState} from 'react';
import { useHistory } from "react-router-dom";
import LoginForm from "../components/LoginForm";

function LoginPage(){

    const [loginData, setLoginData] = useState([]);

    const history = useHistory();

    function loginUserHandler(user){
        fetch("http://localhost:3001/login", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(() => history.replace("/home"))
    }

    useEffect(function(){

    }, [])

    return (
        <div>
            <h1>Login Page</h1>
            <LoginForm loginUser={loginUserHandler}/>
        </div>
    )
}

export default LoginPage;