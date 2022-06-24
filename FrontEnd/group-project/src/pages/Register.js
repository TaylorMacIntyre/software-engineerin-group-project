import { useHistory } from "react-router-dom";
import RegisterForm from "../components/RegisterForm";

function RegisterPage(){

    const history = useHistory();
    
    function registerUserHandler(user){
        fetch("http://localhost:3001/registration", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "COntent-Type": "application/json"
            }
        }).then(() => history.replace("/login"))
    }
    return (
        <div>
            <h1>Registration Page</h1>
            <RegisterForm registerUser={registerUserHandler}/>
        </div>
        
    )
}

export default RegisterPage;