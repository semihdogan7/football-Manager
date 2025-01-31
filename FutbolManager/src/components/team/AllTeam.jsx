import React, { useState, useEffect } from 'react'
import { Link, useLocation } from 'react-router-dom';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import axios from 'axios';
import { useSelector } from "react-redux";

export default function AllTeam() {

    const { localhost } = useSelector(store => store.getDegiskenler)
    const imagesTeam = (id) => `/images/team/${id}.png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;

    const location = useLocation(); // sayfalar arası navigate state ile veri aktarımı
    const { continentProp, countryProp, levelProp } = location.state || {};

    const [continent, setContinent] = useState(continentProp); // combo seçimleri
    const [country, setCountry] = useState(countryProp);
    const [level, setLevel] = useState(levelProp);

    function renderEt() {
        // setContinent(document.getElementById("filterByContinent").value); // göstere basınca combo seçimlerine göre sayfa render edilir
        // setCountry(document.getElementById("filterByCountry").value);
        // setLevel(document.getElementById("filterByLevel").value);
        console.log("butona işlev atanmaddı")
    }

    function sirala(prm) {
        setSiralaMethod(prm);
    }

    const [teamPageble, setTeamPageble] = useState([]);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(20);
    const [totalPages, setTotalPages] = useState(0);
    const [siralaMethod, setSiralaMethod] = useState("totalValue");

    const getTeamPageble = async () => {
        const response = await axios.get(`${localhost}team/teampageble`, {
            params: { page: page, size: size, sirala: siralaMethod }
        });
        setTeamPageble(response.data.content);
        setTotalPages(response.data.totalPages);
    };
    useEffect(() => {
        getTeamPageble();
    }, [page, size, siralaMethod]); // sayfa veya boyut değiştiğinde veri çekmeyi tetikle

    if (teamPageble.length > 0) {
        console.log(teamPageble)
    }

    const buttons = [];
    buttons.push(<Button // material ui button
        key={-1}
        onClick={() => setPage(0)}
        sx={{
            backgroundColor: "rgb(38, 38, 38)",
            color: "rgb(255,255,255)"
        }}
    >{"<<"}</Button>)

    for (let i = 0 + (page < 3 ? 0 : page > 13 ? totalPages - 5 : page - 2);
        i < 5 + (page < 3 ? 0 : page > 13 ? totalPages - 5 : page - 2); i++) {
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

    const uniqueContinents = [...new Set(teamPageble.map((team) => team.continent))]; // combo benzersiz takım sayısı
    const uniqueCountry = [...new Set(teamPageble.map((team) => team.country))]; //

    return (

        <div className="container-fluid table-text" >
            <div className="row">
                <div className="col-12 col-lg-2 genislikFull " >
                    <div className="sticky-panel bg-light p-3">
                        <br /><br />
                        <h5>Filtreleme Paneli</h5>
                        <hr />
                        {/* <!-- Combobox --> */}
                        <div className="mb-3">
                            <label htmlFor="filterByContinent" className="form-label">Kıta</label>
                            <select id="filterByContinent" className="form-select">
                                <option value="Tüm">Tüm Kıtalar</option>
                                {uniqueContinents.map((continent) => (
                                    <option key={continent} value={continent}>{continent}</option>
                                ))}
                            </select>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="filterByCountry" className="form-label">Ülke</label>
                            <select id="filterByCountry" className="form-select">
                                <option value="Tüm">Tüm Ülkeler</option>
                                {uniqueCountry.map((country) => (
                                    <option key={country} value={country}>{country}</option>
                                ))}
                            </select>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="filterByLevel" className="form-label">Lig Seviyesi</label>
                            <select id="filterByLevel" className="form-select">
                                <option value="Tüm">Tüm Ligler</option>
                                <option value="I">I. Ligler</option>
                                <option value="II">II. Ligler</option>
                                <option value="III">II. Ligler</option>
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
                                <th scope="col">Takım</th>
                                <th scope="col">Ülke</th>
                                <th scope="col">Kıta</th>
                                <th className='col-1' scope="col">Lig Seviyesi</th>
                                <th className='col-1' scope="col">Oyuncu</th>
                                <th onClick={() => sirala("totalValue")} className='col-1 allTeamTable' scope="col">Piyasa</th>
                                <th onClick={() => sirala("avrStrong")} className='col-1 allTeamTable' scope="col">Reyting</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                teamPageble.map((team, index) => (
                                    <tr key={team.id}>
                                        <td>{index + 1 + page * 20}</td>
                                        <td>
                                            <img className="card-img-top" src={imagesTeam(team.name)} alt={team.id} style={{ width: "30px" }} />
                                            <Link to={"/team/" + team.name}>{team.name}</Link>
                                        </td>
                                        <td>{
                                            <img className="card-img-top mt-2" src={flagImages(team.country)} alt={team.country} style={{ width: "30px" }} />
                                        } {team.country}</td>
                                        <td>
                                            <img className="card-img-top mt-2" src={flagImages(team.continent)} alt={team.continent} style={{ width: "30px" }} />
                                            {team.continent}</td>
                                        <td>{team.leauge2.split(" ")[team.leauge2.split(" ").length - 1]}. Lig</td>
                                        <td>{team.playerCount}</td>
                                        <td>{team.totalValue} M €</td>
                                        <td className='text-right' >{((team.defStrong + team.midStrong + team.forStrong) / 3).toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</td>
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
