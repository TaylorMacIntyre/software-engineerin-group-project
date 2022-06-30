import React, {useRef} from 'react';
import { useHistory } from "react-router-dom";
import * as yup from 'yup'
import YupPassword from 'yup-password'
YupPassword(yup)


function PasswordForm(props){
    
    const history = useHistory();
    
    const emailRef = useRef();
    const securityQuestionRef = useRef();
    const passwordRef = useRef();

    function submitHandler(event){
        event.preventDefault();
        //Read values
        const email = emailRef.current.value;
        const securityAnswer = securityQuestionRef.current.value;
        const newPw = passwordRef.current.value;
        //password validation
        const user = {email, securityAnswer, newPw}
        //change to have return type, call props only if successfull
        validation(email, newPw, user);
        //Send values to server
        //props.registerUser(user);
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
            var result = props.resetPassword(user);
            //console.log(result);
            
            //return true;
            //  ...
        } catch (e) {
            alert(e.errors);
            console.log(e.errors);
            //return false;
        }
        //end of formik code
        

    }

    return (
        <form onSubmit={submitHandler}>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <label>Security Question: </label>
            <input type="text" required placeholder="Favorite Color?" ref={securityQuestionRef}/>
            <br/>
            <input type="password" required placeholder="New Password" ref={passwordRef}/>
            <br/>
            <button>Submit</button>
        </form>
    );
}

export default PasswordForm;