import React, {useEffect, useState} from 'react';
import { useHistory } from "react-router-dom";
import LoginForm from "../components/LoginForm";

function LoginPage(){

    //stuff I may need
    const [data, setLoginData] = useState([]);

    const history = useHistory();

    function loginUserHandler(user){
        return fetch("http://localhost:9001/user/login", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        })//.then(response => console.log(response.json()))
        /*.then(response => {
            if(response == -1){
                alert("errorrrrr");
            }
        })*/
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
        var loginResponse = data;
        console.log(loginResponse.result);
        if(loginResponse.result != -1){
            history.replace("/home");
        }
        else{
            alert("email or password was incorrect");
        }
        return loginResponse.result;
        })
    }

    return (
        <div>
            <h1>Login Page</h1>
            <LoginForm loginUser={loginUserHandler}/>
        </div>
    )
}

export default LoginPage;