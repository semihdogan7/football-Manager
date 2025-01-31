import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from "react-redux";

export default function Fixture() {
    const ImagesTeam = (id) => `/images/team/${id}.png`;
    const { localhost } = useSelector(store => store.getDegiskenler)
    const { leagueProp } = useParams();
    const [fixture, setFixture] = useState([]);
    const [season, setSeason] = useState(0);
    const [weekRender, setWeekRender] = useState(0);

    useEffect(() => {
        mydata();
    }, [leagueProp]);

    useEffect(() => {
        getFixtureApi();
    }, [weekRender])

    useEffect(() => {
        getFixtureApi();
    }, [season])

    useEffect(() => {
        console.log(fixture);
    }, [fixture])

    async function mydata() {
        const response = await axios.get(`${localhost}database/mydata`);
        setSeason(response.data[16].description);
    }

    const getFixtureApi = async () => {
        const response = await axios.get(`${localhost}match/leaguefixture`, {
            params: { league: leagueProp, season: season }
        });
        setFixture(response.data);
    }

    const weekGroups = fixture.reduce((acc, match) => {
        if (!acc[match.week]) {
            acc[match.week] = []; // Eğer hafta yoksa yeni bir dizi başlat
        }
        acc[match.week].push(match); // Maçı ilgili haftaya ekle
        return acc;
    }, {});

    const [disabledWeeks, setDisabledWeeks] = useState(new Set());

    const handleWeekClick = async (week) => {
        if (disabledWeeks.has(week)) return; // Eğer daha önce tıklandıysa işlem yapma
        setDisabledWeeks(prev => new Set(prev).add(week)); // Hemen devre dışı bırak

        await axios.put(`${localhost}match/updateMatch`, null, {
            params: { season: season, week: week, league: leagueProp }
        });
        console.log(`Hafta ${week} oynatıldı.`);
        setWeekRender(week);
    };

    return (
        <div className="container">
            <div className="row">
                <h3 className='text-center'>{leagueProp} Fixtür</h3>
                {
                    Object.entries(weekGroups).map(([week, matches]) => {
                        const isAllPlayed = matches.every(match => match.isPlayed === "true");
                        const isDisabled = disabledWeeks.has(week) || Object.keys(weekGroups).filter(w => Number(w) <= 14).every(w => disabledWeeks.has(w));

                        return (
                            <div key={week} className="col-12 col-xl-4 col-lg-6 my-3">
                                <div className="card p-3 shadow">
                                    <h4
                                        className={`card-title text-center ${isAllPlayed || isDisabled ? "text-muted" : "clickable"}`}
                                        onClick={() => !isAllPlayed && !isDisabled && handleWeekClick(week)}
                                        style={{ cursor: isAllPlayed || isDisabled ? "not-allowed" : "pointer" }}
                                    >Hafta {week}
                                    </h4>
                                    <div className="card-body">
                                        {matches.map((match, index) => (
                                            <div style={{ height: "35px" }} key={index} className="row d-flex justify-content-between border-bottom py-2">
                                                <div className='text-end col text-fixture'>
                                                    {match.homeName}
                                                </div>
                                                <div className='col-4'>
                                                    <div className='row'>
                                                        <div className='card-text text-center col'><img className="card-img-top" src={ImagesTeam(match.homeName)} alt={match.homeName} style={{ width: "25px" }} /></div>
                                                        <div className='card-text text-center col'>{match.isPlayed === "true" ? match.homeGol + "-" + match.awayGol : "-"}</div>
                                                        <div className='card-text text-center col'><img className="card-img-top" src={ImagesTeam(match.avayName)} alt={match.avayName} style={{ width: "25px" }} /></div>
                                                    </div>
                                                </div>
                                                <div className='card-text col text-start'>{match.avayName}</div>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            </div>
                        );
                    })}
            </div>
        </div>
    );
};
