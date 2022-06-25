import React, {useRef} from 'react';


function PasswordForm(){
    
    const emailRef = useRef();
    const securityQuestionRef = useRef();

    function submitHandler(event){
        event.preventDefault();
        //Read values
        const email = emailRef.current.value;
        const securityQuestion = securityQuestionRef.current.value;
        //password validation
        const user = {email, securityQuestion}
        //change to have return type, call props only if successfull
       // validation(email, password);
        //Send values to server
        //props.registerUser(user);
    }

    return (
        <form onSubmit={submitHandler}>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <br/>
            <label>Security Question: What is your favorite color? </label>
            <input type="text" required placeholder="Favorite Color?" ref={securityQuestionRef}/>
            <br/>
            <button>Submit</button>
        </form>
    );
}

export default PasswordForm;