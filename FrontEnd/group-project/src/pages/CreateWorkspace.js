
import React from 'react';
import { useHistory, useParams } from 'react-router-dom';
import CreateSpaceForm from '../components/CreateSpaceForm';

function CreateWorkSpace() {
    // const id = useParams;
    const history = useHistory();

    function createSpaceHandler(workspace) {
        
        fetch('http://localhost:9000/workspace/saveWorkspace', {
            method: 'POST',
            body: JSON.stringify(workspace),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace('/'));
    }

    return (
        <CreateSpaceForm createSpace={createSpaceHandler} />
    );
};

export default CreateWorkSpace;