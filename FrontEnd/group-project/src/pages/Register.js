import { useHistory } from "react-router-dom";
import RegisterForm from "../components/RegisterForm";

function RegisterPage(){

    const history = useHistory();
    
    function registerUserHandler(user){
        fetch("http://localhost:9000/user/save", {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function(response) {
            return response.json();
        })
        .then(function(data) {
        var registerResponse = data;
        console.log(registerResponse.status);
        if(registerResponse.status === "successful signup"){
            history.replace("/login");
        }
        else{
            alert(registerResponse.status);
        }
        return registerResponse.result;
        })
    }
    return (
        <div>
            <h1>Registration Page</h1>
            <RegisterForm registerUser={registerUserHandler}/>
        </div>
        
    )
}

export default RegisterPage;