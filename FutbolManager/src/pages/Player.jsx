import React from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useSelector } from "react-redux";

export default function player() {

    const { localhost } = useSelector(store => store.getDegiskenler)

    const playerImaages = (id) => `/images/player/player (${id}).png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;

    const navigate = useNavigate();

    const [players, setPlayers] = useState([]);

    const fetchPlayers = async () => {
        try {

            const response = await axios.get(`${localhost}player/playerpage?page=0&size=10`);
            setPlayers(response.data.content); // content kısmını alıyoruz
        } catch (error) {
            console.error("Error fetching players:", error);
        }
    };

    useEffect(() => {
        fetchPlayers();
    }, []); // sayfa veya boyut değiştiğinde veri çekmeyi tetikle

    console.log(players)

    return (
        <div className="container-fluid">

            <div className='row justify-content-evenly ' >

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/player/allplayer", {
                        state: {
                            countryProp: "Tüm",
                        }
                    })}>Oyuncular</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(0, 5).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/player/" + player.id, {
                                    state: {
                                        playerProp: player.id
                                    }
                                })}>
                                    <img className="card-img-top" src={playerImaages(1)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name + " " + player.surname}</p>

                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(5, 10).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/player/" + player.id, {
                                    state: {
                                        playerProp: player.id
                                    }
                                })}>
                                    <img className="card-img-top" src={playerImaages(1)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name + " " + player.surname}</p>

                                </div>
                            ))
                        }
                    </div>



                </div>

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/player/allplayer", {
                        state: {
                            countryProp: "Tüm",
                        }
                    })}>Oyuncular</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(0, 5).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={playerImaages(player.id)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(5, 10).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={playerImaages(player.id)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>


            </div>

            <div className='row justify-content-evenly ' >

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/player/allplayer", {
                        state: {
                            countryProp: "Tüm",
                        }
                    })}>Oyuncular</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(0, 5).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={playerImaages(player.id)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(5, 10).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={playerImaages(player.id)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/player/allplayer", {
                        state: {
                            countryProp: "Tüm",
                        }
                    })}>Oyuncular</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(0, 5).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={playerImaages(player.id)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            players.slice(5, 10).map((player) => (
                                <div key={player.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={playerImaages(player.id)} alt={player.id} />
                                    <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{player.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>


            </div>


        </div>
    )
}
