import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from "react-redux";
import { Card, CardContent, Typography, Paper } from "@mui/material";

export default function Scored() {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const ImagesTeam = (id) => `/images/team/${id}.png`;
    const ImagesPlayer = (id) => `/images/player/player (${id}).png`;

    const { leagueProp } = useParams();

    const [playersGol, setPlayersGol] = useState([]);
    const [playersAsist, setPlayersAsist] = useState([]);
    const [season, setSeason] = useState(0);

    useEffect(() => {
        mydata();
    }, [leagueProp]);

    useEffect(() => {
        getPlayersGol();
        getPlayersAsist();
    }, [season])

    async function mydata() {
        const response = await axios.get(`${localhost}database/mydata`);
        setSeason(response.data[16].description);
    }

    const getPlayersGol = async () => {
        const response = await axios.get(`${localhost}leaplayer/goltop10`, {
            params: { season: season, league: leagueProp }
        });
        setPlayersGol(response.data);
    };

    const getPlayersAsist = async () => {
        const response = await axios.get(`${localhost}leaplayer/asisttop10`, {
            params: { season: season, league: leagueProp }
        });
        setPlayersAsist(response.data);
    };

    const positionMap = {
        "GK": "Kaleci",
        "DL": "Sol Bek",
        "DC": "Stoper",
        "DR": "Sağ Bek",
        "DM": "Ön Libero",
        "MC": "Merkez Orta Saha",
        "ML": "Sol Orta Saha",
        "MR": "Sağ Orta Saha",
        "AMC": "Ofansif Orta Saha",
        "AML": "Sol Kanat",
        "AMR": "Sağ Kanat",
        "ST": "Forvet"
    };

    return (
        <div>
            <div className='row'>
                <div className="card m-3 col-12 col-xl-5">
                    <Paper elevation={4} sx={{ borderRadius: 4, overflow: "hidden", padding: 2 }}>
                        <Card variant="outlined" sx={{ borderRadius: 4, boxShadow: 3, backgroundColor: "#f5f5f5" }}>
                            <CardContent>
                                <Typography className='fw-bold' variant="h5" align="center" gutterBottom>
                                    {leagueProp} Gol Krallığı
                                </Typography>

                                {playersGol.map((player, index) => (
                                    <Paper
                                        key={index}
                                        elevation={4}
                                        sx={{ display: "flex", alignItems: "center", padding: 0, marginBottom: 0.5, borderRadius: 3 }}
                                    >
                                        <div className="col-1 text-center fw-bold">{index + 1}</div>

                                        <div className="col-1 ms-2 text-center">
                                            <img src={ImagesTeam(player.teamName)} alt={player.teamName} style={{ width: "30px" }} />
                                        </div>
                                        <div className="col-1 ms-3 text-center">
                                            <img src={ImagesPlayer(1)} alt={player.teamName} style={{ width: "35px", borderRadius: "50%" }} />
                                        </div>
                                        <div className="col">
                                            <Typography className='ms-2' fontWeight="bold">{player.fullName}</Typography>
                                            <Typography className='ms-2' color="textSecondary">
                                                {positionMap[player.mevki] + " (" + [player.strong] + ")" || "Bilinmeyen"}
                                            </Typography>
                                        </div>
                                        <div className="col-1 text-center fw-bold vurgulu-yazi">{player.goal}</div>
                                    </Paper>
                                ))}
                            </CardContent>
                        </Card>
                    </Paper>
                </div>

                <div className="card m-3 col-12 col-xl-5">
                    <Paper elevation={4} sx={{ borderRadius: 4, overflow: "hidden", padding: 2 }}>
                        <Card variant="outlined" sx={{ borderRadius: 4, boxShadow: 3, backgroundColor: "#f5f5f5" }}>
                            <CardContent>
                                <Typography className='fw-bold' variant="h5" align="center" gutterBottom>
                                    {leagueProp} Asist Krallığı
                                </Typography>

                                {playersAsist.map((player, index) => (
                                    <Paper
                                        key={index}
                                        elevation={4}
                                        sx={{ display: "flex", alignItems: "center", padding: 0, marginBottom: 0.5, borderRadius: 3 }}
                                    >
                                        <div className="col-1 text-center fw-bold">{index + 1}</div>

                                        <div className="col-1 ms-2 text-center">
                                            <img src={ImagesTeam(player.teamName)} alt={player.teamName} style={{ width: "30px" }} />
                                        </div>
                                        <div className="col-1 ms-3 text-center">
                                            <img src={ImagesPlayer(1)} alt={player.teamName} style={{ width: "35px", borderRadius: "50%" }} />
                                        </div>
                                        <div className="col">
                                            <Typography className='ms-2' fontWeight="bold">{player.fullName}</Typography>
                                            <Typography className='ms-2' color="textSecondary">
                                                {positionMap[player.mevki] + " (" + [player.strong] + ")" || "Bilinmeyen"}
                                            </Typography>
                                        </div>
                                        <div className="col-1 text-center fw-bold vurgulu-yazi">{player.asist}</div>
                                    </Paper>
                                ))}
                            </CardContent>
                        </Card>
                    </Paper>
                </div>
            </div>


        </div>
    )
}
