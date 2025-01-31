import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from "react-redux";


export default function LeaderboardLeague() {
    const [season, setSeason] = useState(0);

    const { localhost } = useSelector(store => store.getDegiskenler)
    const ImagesTeam = (id) => `/images/team/${id}.png`;

    const { leagueProp } = useParams();

    const [teams, setTeams] = useState([]);

    useEffect(() => {
        leaderboardTeam();
    }, [season])

    useEffect(() => {
        mydata();
    }, [leagueProp]);

    async function leaderboardTeam() {
        const response = await axios.get(`${localhost}leateam/getleague`, {
            params: { season: season, league: leagueProp }
        });
        setTeams(response.data);
    }

    async function mydata() {
        const response = await axios.get(`${localhost}database/mydata`);
        setSeason(response.data[16].description);
    }

    return (
        <div>
            <table className="table table-hover">
                <thead className="table-light">
                    <tr>
                        <th className='' scope="col">#</th>
                        <th scope="col-4">Takım</th>
                        <th scope="col">O</th>
                        <th scope="col">G</th>
                        <th className='col' scope="col">B</th>
                        <th className='col' scope="col">M</th>
                        <th className='col' scope="col">A</th>
                        <th className='col' scope="col">Y</th>
                        <th className='col' scope="col">Av</th>
                        <th className='col' scope="col">P</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        teams.map((team, index) => (
                            <tr key={team.id}>
                                <td>{index + 1}</td>
                                <td>
                                    <img className="card-img-top" src={ImagesTeam(team.teamName)} alt={team.teamName} style={{ width: "25px" }} />
                                    <Link target="_blank" to={`/team/${team.teamName}`}>{" " + team.teamName + " (" + team.itibar + ")"}</Link></td>

                                <td>{team.match}</td>
                                <td>{team.winn}</td>
                                <td>{team.draw}</td>
                                <td>{team.loss}</td>
                                <td>{team.golScore}</td>
                                <td>{team.golCon}</td>
                                <td>{team.averaj > 0 ? " +" + team.averaj : team.averaj}</td>
                                <td>{team.puan}</td>

                            </tr>
                        ))
                    }


                </tbody>
            </table>
        </div>
    )
}
