import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import ViewSpace from '../components/ViewWorkSpace';



function WorkSpace() {
    
    const {uid} = useParams();
    const [spacesData, setSpaceData] = useState([]);
    const url1 = `http://localhost:9000/workspace/getAllWorkspaces/${uid}`

    useEffect(function () {
        fetch(url1,{method:'GET'})
            .then(response => response.json())
            .then(spaces => {
                setSpaceData(spaces);
            });
    }, [url1]);


    return (
        <section>
            <ViewSpace space={spacesData} />
        </section>
    );
};

export default WorkSpace;