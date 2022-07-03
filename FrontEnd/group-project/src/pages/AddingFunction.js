import React from 'react';
import { useHistory } from 'react-router-dom';
import AddingForm from '../components/Addingmember';

function AddingFunction() {
    const uid = localStorage.getItem("uid")
    const workid = localStorage.getItem("workid")
    const history = useHistory();
    function AddingHandler(ID){
        fetch(`http://localhost:9000/workspace/addUserToWorkspace/${workid}?user_id=` + ID, {
            method: 'PUT',
            body: JSON.stringify(ID),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace('/WorkSpace/'+ uid));
    }

    return(
        <AddingForm Addingmember ={AddingHandler}/>
    );
}

export default AddingFunction;