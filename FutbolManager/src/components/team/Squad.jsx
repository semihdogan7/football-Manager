import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from 'react-redux';


export default function Squad() {

    const playerImaages = (id) => `/images/player/player (${id}).png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;
    const teamImages = (id) => `/images/team/${id}.png`;

    const { localhost } = useSelector(store => store.getDegiskenler)
    const { teamProp } = useParams();
    const [players, setPlayers] = useState([]);

    useEffect(() => {
        const fetchPlayers = async () => {
            const response = await axios.get(`${localhost}player/playerthisteam`, {
                params: { team2: teamProp },
            });
            setPlayers(response.data);
        };
        fetchPlayers();
    }, [teamProp]);

    useEffect(() => {
        console.log(players)
    }, [players])

    return (
        <div className=''>
            <table className="table table-dark table-hover">
                <thead className="table-light">
                    <tr className=''>
                        <th scope="col">Role</th>
                        <th scope="col">Oyuncu</th>
                        <th scope="col">Ülke</th>
                        <th scope="col">Maç</th>
                        <th scope="col">Gol</th>
                        <th scope="col">Asist</th>
                        <th scope="col">Takım</th>
                        <th scope="col">Poz</th>
                        <th scope="col">Yaş</th>
                        <th className='text-center' scope="col">Overall</th>
                        <th className='text-end' scope="col">Piyasa</th>

                    </tr>
                </thead>
                <tbody >
                    {
                        players.map((player, index) => (

                            <tr className='bg-warning' key={player.id}>
                                <td>{player.role}</td>
                                <td style={{ width: "25%" }} className='table-text'>
                                    <Link to={"/player/" + player.id}> {"  " + player.name + " " + player.surname}</Link>
                                </td>

                                <td>{
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "25px" }} />
                                }</td>
                                <td>{player.sezonMac}</td>
                                <td>{player.sezonGol}</td>
                                <td>{player.sezonAsist}</td>
                                <td>
                                    <img className="card-img-top mt-2" src={teamImages(player.team2)} alt={player.team2} style={{ width: "25px" }} />
                                </td>
                                <td>{player.position}</td>
                                <td>{player.agePlayer}</td>
                                <td className='text-center'>{player.strong}</td>
                                <td className='text-end' >{player.value.toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })} M €</td>

                            </tr>
                        ))
                    }

                </tbody>
            </table>
        </div>
    )
}
