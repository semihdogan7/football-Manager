import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from "react-redux";

export default function Team() {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const navigate = useNavigate();

    const [teams, setTeams] = useState([]);


    const teamImages = (id) => `/images/team/${id}.png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;

    const getApi = async () => {
        try {
            const response = await axios.get(localhost + "team/teampageble",
                { params: { page: 0, size: 10, sirala: "totalValue" } });
            setTeams(response.data.content);
        } catch (error) {
            console.log("Error fetching fixture:", error);
        }
    }

    useEffect(() => {
        getApi();
    }, [])
    console.log(teams)

    return (
        <div className="container-fluid">

            <div className='row justify-content-evenly ' >

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/team/allteam", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Takımlar</h4>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(0, 5).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/team/" + team.name, {
                                    state: {
                                        teamProp: team.name
                                    }
                                })}>
                                    <img className="card-img-top" src={teamImages(team.name)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(5, 10).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.name)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                </div>

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/team/allteam", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Takımlar</h4>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(0, 5).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.id)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(5, 10).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.id)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                </div>


            </div>

            <div className='row justify-content-evenly ' >

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/team/allteam", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Takımlar</h4>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(0, 5).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.id)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(5, 10).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.id)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                </div>

                <div className='col-11 col-lg-5 m-4 d-flex flex-column align-items-center ' >
                    <h4 className='txtTitle' onClick={() => navigate("/team/allteam", {
                        state: {
                            continentProp: "Tüm",
                            countryProp: "Tüm",
                            levelProp: "Tüm",
                        }
                    })}>Takımlar</h4>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(0, 5).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.id)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                    <div className='row justify-content-evenly m-1'>
                        {
                            teams.slice(5, 10).map((team) => (
                                <div key={team.id} className="card col-2 cardLeauge" onClick={() => navigate("/?????")}>
                                    <img className="card-img-top" src={teamImages(team.id)} alt={team.id} />
                                    <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "40%" }} />
                                    <p className="card-text text-center">{team.name}</p>
                                </div>
                            ))
                        }
                    </div>

                </div>


            </div>

        </div>
    )
}
