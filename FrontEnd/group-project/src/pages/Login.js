import React, {useEffect, useState} from 'react';
import { useHistory } from "react-router-dom";
import LoginForm from "../components/LoginForm";

function LoginPage(){

    //stuff I may need
    const [data, setLoginData] = useState([]);

    const history = useHistory();

    function loginUserHandler(email, password){
        fetch("http://localhost:9001/user/login/:" + email + "/:" + password, {
            /*method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }*/
        }).then(response => console.log(response.text()))
        .then(data => console.log(data))//.then(() => history.replace("/home"))
        //console.log(response);
        console.log("data" + data);
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