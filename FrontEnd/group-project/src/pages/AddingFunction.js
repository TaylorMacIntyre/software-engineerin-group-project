import React from 'react';
import { useHistory } from 'react-router-dom';
import AddingForm from '../components/Addingmember';
import { toast } from 'react-toastify';
function AddingFunction() {
    const uid = localStorage.getItem("uid")
    const workid = localStorage.getItem("workid")
    const history = useHistory();
    
    function AddingHandler(email){
        fetch(`http://localhost:9000/workspace/addUserToWorkspace/${workid}?email=` + email, {
            method: 'PUT',

            body: JSON.stringify(email),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((r) => {
            if(r.ok) return r.text();
            throw new Error("Failed")
        })
        .then(() => history.replace('/WorkSpace/'+ uid))
        .catch(e => toast.error("Adding user failed"))
        
        
        
        
    }

    return(
        <AddingForm Addingmember ={AddingHandler}/>
    );
}

export default AddingFunction;