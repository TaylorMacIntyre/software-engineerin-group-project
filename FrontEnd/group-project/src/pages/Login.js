import React, {useEffect, useState} from 'react';
import { useHistory } from "react-router-dom";
import LoginForm from "../components/LoginForm";

function LoginPage(){

    //stuff I may need
    const [data, setLoginData] = useState([]);

    const history = useHistory();

    function loginUserHandler(user){
        fetch("http://localhost:3001/user/login", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
        .then(data => setLoginData(data))//.then(() => history.replace("/home"))
        console.log(data);
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