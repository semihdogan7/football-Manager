import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import { useSelector } from "react-redux";
import axios from 'axios';

export default function AllLeague() {

    const leagueImages = (id) => `/images/league/${id}.png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;

    const { localhost } = useSelector(store => store.getDegiskenler)
    const [leagues, setLeague] = useState([]);


    useEffect(() => {
        apiFunction();
    }, [])

    const apiFunction = async () => {
        const response = await axios.get(`${localhost}league/all`);
        setLeague(response.data)
    };

    const uniqueContinents = [...new Set(leagues.map((lig) => lig.continent))]; // combo için benzersiz takım sayısı
    const uniqueCountry = [...new Set(leagues.map((lig) => lig.country))]; //

    const location = useLocation(); // sayfalar arası navigate state ile veri aktarımı
    const { continentProp, countryProp, levelProp } = location.state || {};

    const [continent, setContinent] = useState(continentProp); // combo seçimleri
    const [country, setCountry] = useState(countryProp);
    const [level, setLevel] = useState(levelProp);

    function renderEt() {
        setContinent(document.getElementById("filterByContinent").value); // göstere basınca combo seçimlerine göre sayfa render edilir
        setCountry(document.getElementById("filterByCountry").value);
        setLevel(document.getElementById("filterByLevel").value);
        setPNum(0);
    }

    const [pNum, setPNum] = useState(0);

    const sayfaSayisi = Math.ceil(
        leagues.filter(lig =>
            (lig.continent == continent || continent == "Tüm") &&
            (lig.country == country || country == "Tüm") &&
            (lig.level == level || level == "Tüm")
        ).length / 25);

    const buttons = [];

    for (let i = 0; i < sayfaSayisi; i++) {
        buttons.push(
            <Button // material ui button
                key={i + 1}
                onClick={() => setPNum(i)}
                sx={{
                    background: pNum === i ? "blue" : "white",
                    color: pNum === i ? "white" : "black"
                }}
            >{i + 1}</Button>
        )
    }

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

                        <div >
                            <ButtonGroup orientation='horizontal' sx={{
                                display: 'grid',
                                gridTemplateColumns: 'repeat(auto-fit, minmax(38px, 50px))', // Eşit genişlikte butonlar
                                gap: '2px', // Butonlar arası boşluk flexWrap: "wrap"
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
                                <th scope="col">Müsabaka</th>
                                <th scope="col">Ülke</th>
                                <th scope="col">Kıta</th>
                                <th className='col-1' scope="col">Lig Seviyesi</th>
                                <th className='col-1' scope="col">Takım</th>
                                <th className='col-1' scope="col">Oyuncu</th>
                                <th className='col-1' scope="col">Piyasa</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                leagues.filter(lig =>
                                    (lig.continent == continent || continent == "Tüm") &&
                                    (lig.country == country || country == "Tüm") &&
                                    (lig.level == level || level == "Tüm")
                                ).slice(pNum * 25, pNum * 25 + 25).map((lig, index) => (
                                    // <ProductComponents key={prd.id} productProps={prd}></ProductComponents>
                                    <tr key={lig.id}>
                                        <td>{index + 1 + pNum * 20}</td>
                                        <td>
                                            <img className="" src={leagueImages(lig.name)} alt={lig.name} style={{ width: "30px" }} />
                                            <Link to={`/league/${lig.name}`}>{lig.name}</Link>
                                        </td>
                                        <td>
                                            <img className="" src={flagImages(lig.country)} alt={lig.country} style={{ width: "30px" }} />
                                            {lig.country}
                                        </td>
                                        <td>
                                            <img className="" src={flagImages(lig.continent)} alt={lig.continent} style={{ width: "30px" }} />
                                            {lig.continent}
                                        </td>
                                        <td>{lig.level}. Lig</td>
                                        <td>{lig.teamCount}</td>
                                        <td>{lig.playerCount}</td>
                                        <td className='text-right' >{lig.value} M €</td>
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
