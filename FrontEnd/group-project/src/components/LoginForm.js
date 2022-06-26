import React, {useRef} from 'react';
import { useHistory } from "react-router-dom";

function LoginForm(props){

    const history = useHistory();

    const emailRef = useRef();
    const passwordRef = useRef();

    function submitHandler(event){
        event.preventDefault();

        const email = emailRef.current.value;
        const password = passwordRef.current.value;

        const user = {email, password}

        //Send values to server
        props.loginUser(user);
    }

    function forgotPassword(event){
        event.preventDefault();
        history.replace("/ForgotPassword")
    }

    return (
        <form onSubmit={submitHandler}>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <input type="password" required placeholder="Password" ref={passwordRef}/>
            <br/>
            <button onClick={forgotPassword}>Forgot Password</button>
            <button>Submit</button>
        </form>
    );
}

export default LoginForm;