import React, { useEffect, useState } from 'react'
import { useSelector } from "react-redux";
import axios from 'axios';

export default function Home() {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const [season, setSeason] = useState(0);

    useEffect(() => {
        apiFunction();
    }, [])

    const apiFunction = async () => {
        const response = await axios.get(`${localhost}database/mydata`);
        console.log(response.data)
        setSeason(response.data[16].description)
    };

    return (
        <div>
            <h1>{season}. sezon</h1>
        </div>
    )
}
