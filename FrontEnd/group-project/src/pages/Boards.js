import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ViewBoards from '../components/ViewBoards';


function Boards() {
    const {id} = useParams();
    console.log({id})
    const [boardsData, setBoardsData] = useState([]);
    const url = `http://localhost:9000/workspace/getWorkspaceBoards/${id}`

    // function getAllBoards() {
    //     fetch(`http://localhost:9000/board/getAllBoards`,{method:'GET'})
    //         .then(response => response.json())
    //         .then(boards => {
    //             setBoardsData(boards);
    //         });
    // };

    useEffect(function () {
        fetch(url,{method:'GET'})
        .then(response => response.json())
        .then(boards => {
            setBoardsData(boards);
        });

    },[url]);


    return (
        <section>
            <ViewBoards boards={boardsData} />
        </section>
    );
};

export default Boards;