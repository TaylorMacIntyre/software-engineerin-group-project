
import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateBoardForm from '../components/CreateBoardForm';
import ViewSpace from '../components/ViewWorkSpace';

function CreateBoard() {
    
    const history = useHistory();

    function createBoardHandler(board) {
        
        fetch('http://localhost:9000/board/saveBoard', {
            method: 'POST',
            body: JSON.stringify(board),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => history.replace(`/boards`));
        
    }

    function assign(a,b){
        fetch(`http://localhost:9000/workspace/assignBoard/${a}?board_id=${b}`,{
            method: 'PUT',
            
        }).then(() => history.replace(`/boards/${a}`));
    }


    return (
        <CreateBoardForm createBoard={createBoardHandler} assign/>
        
    );
};

export default CreateBoard;