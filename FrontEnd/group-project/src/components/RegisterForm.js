import React, {useRef} from 'react';
import * as yup from 'yup'
import YupPassword from 'yup-password'
YupPassword(yup)

function RegisterForm(props){
    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const emailRef = useRef();
    const passwordRef = useRef();
    const securityQuestionRef = useRef();

    function submitHandler(event){
        event.preventDefault();
        //Read values
        const firstName = firstNameRef.current.value;
        const lastName = lastNameRef.current.value;
        const email = emailRef.current.value;
        const password = passwordRef.current.value;
        const securityQuestion = securityQuestionRef.current.value;
        //password validation
        const user = {firstName, lastName, email, password, securityQuestion}
        //change to have return type, call props only if successfull
        validation(email, password);
        //Send values to server
        props.registerUser(user);
    }
    async function validation(email, password){
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
            //  ...
        } catch (e) {
            alert(e.errors);
            console.log(e.errors) // => [
            //   'password must be at least 8 characters',
            //   'password must contain at least 1 uppercase letter',
            //   'password must contain at least 1 number',
            //   'password must contain at least 1 symbol',
            // ]
        }
        //end of formik code
        

    }
    
    return (
        <form onSubmit={submitHandler}>
            <input type="text" required placeholder="First Name" ref={firstNameRef}/>
            <br/>
            <input type="text" required placeholder="Last Name" ref={lastNameRef}/>
            <br/>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <input type="password" required placeholder="Password" ref={passwordRef}/>
            <br/>
            <label>Security Question: What is your favorite color? </label>
            <input type="text" required placeholder="Favorite Color?" ref={securityQuestionRef}/>
            <br/>
            <button>Submit</button>
        </form>
    );
}

export default RegisterForm;