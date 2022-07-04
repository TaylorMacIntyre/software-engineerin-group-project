
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateSpaceForm from '../components/CreateSpaceForm';

function CreateWorkSpace() {
    // const id = useParams;
    const history = useHistory();
    const uid = localStorage.getItem("uid");
    function createSpaceHandler(workspace) {
        
        fetch(`http://localhost:9000/workspace/saveWorkspace/${uid}`, {
            method: 'POST',
            body: JSON.stringify(workspace),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/WorkSpace/${uid}`));
    }

    return (
        <CreateSpaceForm createSpace={createSpaceHandler} />
    );
};

export default CreateWorkSpace;