import React, { useEffect, useState } from 'react';
import ViewBoards from '../components/ViewBoards';
import boards from '../data/data';

function Boards() {
    const [boardsData, setBoardsData] = useState([]);

    function getAllBoards() {
        fetch('http://localhost:9000/board/getAllBoards',{method:'GET'})
            .then(response => response.json())
            .then(boards => {
                setBoardsData(boards);
            });
    };

    useEffect(function () {
        getAllBoards();
    }, []);


    return (
        <section>
            <ViewBoards boards={boardsData} />
        </section>
    );
};

export default Boards;