import { useHistory } from "react-router-dom";
import PasswordForm from "../components/PasswordForm";

function PasswordPage(){

    return (
        <div>
            <h1>Reset your password</h1>
            <PasswordForm/>
        </div>
        
    )
}

export default PasswordPage;