import React, {useRef} from 'react';

function RegisterForm(){
    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const emailRef = useRef();
    const passwordRef = useRef();

    function submitHandler(event){
        event.preventDefault();
        //Read values
        const firstName = firstNameRef.current.value;
        const lastName = lastNameRef.current.value;
        const email = emailRef.current.value;
        const password = passwordRef.current.value;
        const user = {firstName, lastName, email, password}
        //console.log(user);
        //Send values to server
    }
    
    return (
        <form onSubmit={submitHandler}>
            <input type="text" required placeholder="First Name" ref={firstNameRef}/>
            <input type="text" required placeholder="Last Name" ref={lastNameRef}/>
            <input type="email" required placeholder="Email" ref={emailRef}/>
            <input type="password" required placeholder="Password" ref={passwordRef}/>
            <button>Submit</button>
        </form>
    );
}

export default RegisterForm;