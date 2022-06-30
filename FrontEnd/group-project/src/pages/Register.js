import { useHistory } from "react-router-dom";
import RegisterForm from "../components/RegisterForm";

function RegisterPage(){

    const history = useHistory();
    
    function registerUserHandler(user){
        fetch("http://localhost:9001/user/save", {
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
        console.log(registerResponse.result);
        if(registerResponse.result != -1){
            history.replace("/login");
        }
        else{
            alert("There was an error registering, please try again");
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