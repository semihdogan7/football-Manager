import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import axios from 'axios';
import { useSelector } from "react-redux";

export default function AllPlayer() {

    const [players, setPlayers] = useState([]);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(25);
    const [totalPages, setTotalPages] = useState(0);
    const { localhost } = useSelector(store => store.getDegiskenler)

    const fetchPlayers = async () => {
        try {
            const response = await axios.get(`${localhost}player/playerpage?page=${page}&size=${size}`);
            setPlayers(response.data.content); // content kısmını alıyoruz
            setTotalPages(response.data.totalPages); // toplam sayfa sayısını alıyoruz
        } catch (error) {
            console.error("Error fetching players:", error);
        }
    };

    const playerImaages = (id) => `/images/player/player (${id}).png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;
    const teamImages = (id) => `/images/team/${id}.png`;

    useEffect(() => {
        fetchPlayers();
    }, [page, size]); // sayfa veya boyut değiştiğinde veri çekmeyi tetikle


    function renderEt() {
        // setCountry(document.getElementById("filterByCountry").value);
        // setPage(0);
        console.log("butona henüz işlem tapılmadı")
    }

    const uniqueCountry = [...new Set(players.map((team) => team.country))]; //

    const location = useLocation(); // sayfalar arası navigate state ile veri aktarımı
    const { countryProp } = location.state || {};


    const buttons = [];
    buttons.push(<Button // material ui button
        key={-1}
        onClick={() => setPage(0)}
        sx={{
            backgroundColor: "rgb(38, 38, 38)",
            color: "rgb(255,255,255)"
        }}
    >{"<<"}</Button>)

    for (let i = 0 + (page < 3 ? 0 : page > 347 ? totalPages - 5 : page - 2);
        i < 5 + (page < 3 ? 0 : page > 347 ? totalPages - 5 : page - 2); i++) {
        buttons.push(
            <Button // material ui button
                key={i + 1}
                onClick={() => setPage(i)}
                sx={{
                    //fontSize: "11px",
                    background: page === i ? "rgb(117, 115, 115)" : "white",
                    color: page === i ? "white" : "black"
                }}
            >{i + 1}</Button>
        )
    }
    buttons.push(<Button // material ui button
        key={totalPages + 1}
        onClick={() => setPage(totalPages - 1)}
        sx={{
            backgroundColor: "rgb(38, 38, 38)",
            color: "rgb(255,255,255)"
        }}
    >{">>"}</Button>)




    console.log(players)
    return (
        <div className="container-fluid table-text" >
            <div className="row">
                <div className="col-12 col-lg-2 genislikFull " >
                    <div className="sticky-panel bg-light p-3">
                        <br /><br />
                        <h5>Filtreleme Paneli</h5>
                        <hr />
                        <div className="mb-3">
                            <label htmlFor="filterByCountry" className="form-label">Ülke</label>
                            <select id="filterByCountry" className="form-select">
                                <option value="Tüm">Tüm Ülkeler</option>
                                {uniqueCountry.map((country) => (
                                    <option key={country} value={country}>{country}</option>
                                ))}
                            </select>
                        </div>
                        <button className='btn btn-outline-dark' onClick={renderEt} style={{ float: 'right' }}>Göster</button>
                        <br />
                        <br />
                        <hr />
                        <div key={"butonGroupDiv"} style={{ width: "100%" }}>
                            <ButtonGroup sx={{
                                display: "flex",
                                justifyContent: "space-between", // Butonlar arasında eşit boşluk
                                width: "100%", // Parent div'in genişliğine uyum
                            }}>
                                {buttons}
                            </ButtonGroup>
                        </div>
                    </div>
                </div>
                <div className="col-12 col-lg-10 genislikFull">
                    <table className="table table-hover">
                        <thead className="table-light">
                            <tr>
                                <th className='' scope="col">#</th>
                                <th scope="col">Oyuncu</th>
                                <th scope="col">Mevki</th>
                                <th scope="col">Ülke</th>
                                <th scope="col">Takım</th>
                                <th className='col-1' scope="col">Yaş</th>
                                <th className='col-1' scope="col">Piyasa</th>
                                <th className='col-1' scope="col">Reyting</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                players.map((player, index) => (
                                    <tr key={player.id}>
                                        <td>{index + 1 + page * 25}</td>
                                        <td>
                                            <img className="card-img-top" src={playerImaages(1)} alt={player.id} style={{ width: "30px" }} />
                                            <Link to={"/player/" + player.id}> {"  " + player.name + " " + player.surname}</Link>
                                        </td>
                                        <td>{player.position}</td>
                                        <td>{
                                            <img className="card-img-top mt-2" src={flagImages(player.country)} alt={player.country} style={{ width: "30px" }} />
                                        } {player.country}</td>
                                        <td>
                                            <img className="card-img-top mt-2" src={teamImages(player.team2)} alt={player.team2} style={{ width: "30px" }} />
                                            {player.team2}</td>
                                        <td>{player.agePlayer}</td>
                                        <td>{player.value.toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })} M €</td>
                                        <td>{player.strong}</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
