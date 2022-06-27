import React, {useRef} from 'react';
import { useHistory } from "react-router-dom";
import * as yup from 'yup'
import YupPassword from 'yup-password'
YupPassword(yup)

function LoginForm(props){

    const history = useHistory();

    const emailRef = useRef();
    const passwordRef = useRef();

    function submitHandler(event){
        event.preventDefault();

        const email = emailRef.current.value;
        const password = passwordRef.current.value;

        const user = {email, password}

        validation(email, password, user);

        //Send values to server
        
    }

    async function validation(email, password, user){
        //start of formik code
        const schema = yup.object().shape({
            username: yup.string().email().required(),
            password: yup.string().password().required(),
        })

        const input = {
            username: email,
            password: password,
        }
        
        try {
            // validate
            const res = await schema.validate(input, { abortEarly: false })
            props.loginUser(email, password);
            //  ...
        } catch (e) {
            alert(e.errors);
            console.log(e.errors);
        }
        //end of formik code
        

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