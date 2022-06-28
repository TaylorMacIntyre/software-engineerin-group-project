import React, { useEffect, useState } from 'react';
import ViewSpace from '../components/ViewWorkSpace';
import space from '../data/space';

function WorkSpace() {
    const [boardsData, setBoardsData] = useState([]);

    function getAllSpace() {
        // fetch('http://localhost:9001/board')
        //     .then(response => response.json())
        //     .then(boards => {
        //         setBoardsData(boards);
        //     });
    };

    // useEffect(function () {
    //     getAllSpace();
    // }, []);


    return (
        <section>
            <ViewSpace boards={space} />
        </section>
    );
};

export default WorkSpace;