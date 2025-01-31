import React from 'react'
import { useNavigate } from 'react-router-dom';
import { useSelector } from "react-redux";
import { useState, useEffect } from 'react';
import axios from 'axios';



export default function League() {

    const navigate = useNavigate();

    const { localhost } = useSelector(store => store.getDegiskenler)
    const [leagues, setLeague] = useState([]);

    const leagueImages = (id) => `/images/league/${id}.png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;

    useEffect(() => {
        apiFunction();
    }, [])

    const apiFunction = async () => {
        const response = await axios.get(`${localhost}league/all`);
        console.log(response.data)
        setLeague(response.data)
    };



    return (
        <div className="container-fluid">

            <div className='row justify-content-evenly ' >

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/league/allleague", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Ulusal Ligler</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(0, 5).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/league/" + leauge.name)}>
                                    <img className="card-img-top" src={leagueImages(leauge.name)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(5, 10).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/league/" + leauge.name)}>
                                    <img className="card-img-top" src={leagueImages(leauge.name)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>
                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/league/allleague", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Ulusal Ligler</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(0, 5).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={leagueImages(leauge.id)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(5, 10).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={leagueImages(leauge.id)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>

            </div>

            <div className='row justify-content-evenly ' >

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/league/allleague", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Ulusal Ligler</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(0, 5).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={leagueImages(leauge.id)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(5, 10).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={leagueImages(leauge.id)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>
                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/league/allleague", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Ulusal Ligler</h4>


                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(0, 5).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={leagueImages(leauge.id)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            leagues.slice(5, 10).map((leauge) => (
                                <div key={leauge.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={leagueImages(leauge.id)} alt={leauge.id} />
                                    <img className="card-img-top mt-2" src={flagImages(leauge.country)} alt={leauge.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{leauge.name}</p>
                                </div>
                            ))
                        }
                    </div>



                </div>

            </div>


        </div>
    )
}
