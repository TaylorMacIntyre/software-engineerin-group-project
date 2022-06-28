
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateBoardForm from '../components/CreateBoardForm';

function CreateBoard() {

    const history = useHistory();

    function createBoardHandler(board) {
        
        // fetch('http://localhost:9001/board', {
        //     method: 'POST',
        //     body: JSON.stringify(board),
        //     headers: {
        //         'Content-Type': 'application/json'
        //     }
        // }).then(() => history.replace('/boards'));
    }

    return (
        <CreateBoardForm createBoard={createBoardHandler} />
    );
};

export default CreateBoard;