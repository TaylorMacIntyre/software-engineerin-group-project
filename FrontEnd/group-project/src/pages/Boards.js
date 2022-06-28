import React, { useEffect, useState } from 'react';
import ViewBoards from '../components/ViewBoards';
import boards from '../data/data';

function Boards() {
    const [boardsData, setBoardsData] = useState([]);

    function getAllBoards() {
        // fetch('http://localhost:9001/board')
        //     .then(response => response.json())
        //     .then(boards => {
        //         setBoardsData(boards);
        //     });
    };

    // useEffect(function () {
    //     getAllBoards();
    // }, []);


    return (
        <section>
            <ViewBoards boards={boards} />
        </section>
    );
};

export default Boards;