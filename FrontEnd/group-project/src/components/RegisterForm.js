import React, {useRef} from 'react';

function RegisterForm(){
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
        //console.log(user);
        //Send values to server
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
            <input type="text" required placeholder="Favorite Color?" ref={lastNameRef}/>
            <br/>
            <button>Submit</button>
        </form>
    );
}

export default RegisterForm;