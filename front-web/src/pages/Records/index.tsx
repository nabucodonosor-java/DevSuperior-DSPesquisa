import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Pagination from 'components/Pagination';
import { RecordResponse } from './types';
import { formtDate } from './helpers';
import Filters from 'components/Filters';
import './styles.css';

const BASE_URL = 'http://localhost:8080';

const Records = () => {

    const [recordsRespose, setRecordsResponse] = useState<RecordResponse>();
    const [activePage, setActivePage] = useState(0);

    useEffect(() => {
        axios.get(`${BASE_URL}/records?linesPerPage=12&page=${activePage}`)
        .then(response => setRecordsResponse(response.data));
    }, [activePage]);

    const handlePageChange = (index: number) => {
        setActivePage(index);
    }
 
    return (
        <div className=""> 
            <Filters link="/charts" linkText="VER GRÁFICOS" /> 
            <table className="records-table" cellPadding="0" cellSpacing="0">
                <thead>
                    <tr>
                        <th>DATA</th>
                        <th>NOME</th>
                        <th>IDADE</th>
                        <th>PLATAFORMA</th>
                        <th>GÊNERO</th>
                        <th>TÍTULO DO GAME</th>
                    </tr>
                </thead>
                <tbody>
                    {recordsRespose?.content.map(record => (
                         <tr key={record.id}>
                            <td>{formtDate(record.moment)}</td>
                            <td>{record.name}</td>
                            <td>{record.age}</td>
                            <td className="text-secondary">{record.gamePlatform}</td>
                            <td>{record.genreName}</td>
                            <td className="text-primary">{record.gameTitle}</td>
                        </tr>
                    ))}    
                </tbody>
            </table>
            <Pagination 
            activePage={activePage}
            goToPage={handlePageChange}
            totalPages={recordsRespose?.totalPages} />               
        </div>
    );
}

export default Records;