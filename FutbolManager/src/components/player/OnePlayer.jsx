import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import axios from 'axios';
// import RadarChart from './RadarChart';
import RadarChart from '../../redux/RadarChart';
import { useSelector } from "react-redux";

export default function OnePlayer() {

    const { localhost } = useSelector(store => store.getDegiskenler)
    const playerImaages = (id) => `/images/player/player (${id}).png`;
    const flagImages = (id) => `/images/flag/w80/${id}.png`;
    const teamImages = (id) => `/images/team/${id}.png`;

    const { playerProp } = useParams();
    const [playerId, setPlayerId] = useState(playerProp);
    const [player, setPlayer] = useState({
        playerAttributes: {}, playerPositions: {}
    });
    const [attributes, setAttributes] = useState({
        Savunma: 0, Fiziksel: 0, Sürat: 0, Vizyon: 0, Hücum: 0, Teknik: 0, Hava: 0, Zihinsel: 0
    });


    const getApi = async () => {
        const response = await axios.get(localhost + "player/getplayer",
            { params: { id: playerProp }, });
        setPlayer(response.data);
    }

    useEffect(() => {
        console.log("1 -> ", playerId)
        getApi(playerId)
    }, [playerId])


    useEffect(() => {
        console.log("2-> ", player)
        setAttributes({
            Savunma: (player.playerAttributes.topKapma + player.playerAttributes.topKesme + player.playerAttributes.markaj) / 3,
            Fiziksel: (player.playerAttributes.guc + player.playerAttributes.dayaniklilik + player.playerAttributes.ceviklik) / 3,
            Sürat: (player.playerAttributes.dripling + player.playerAttributes.hiz + player.playerAttributes.aniHiz) / 3,
            Vizyon: (player.playerAttributes.orta + player.playerAttributes.pas + player.playerAttributes.gorus) / 3,
            Hücum: (player.playerAttributes.bitiricilik + player.playerAttributes.sut + player.playerAttributes.plase) / 3,
            Teknik: (player.playerAttributes.yetenek + player.playerAttributes.topKontrolu + player.playerAttributes.falso) / 3,
            Hava: (player.playerAttributes.kafaIsabeti + player.playerAttributes.kafaVurusu + player.playerAttributes.ziplama) / 3,
            Zihinsel: (player.playerAttributes.liderlik + player.playerAttributes.sogukkanlilik + player.playerAttributes.kararlilik) / 3,
        })
    }, [player])


    const getClassName = (value) => {
        if (value <= 5) return "kucuk5";
        if (value <= 10) return "kucuk10";
        if (value <= 15) return "kucuk15";
        if (value <= 20) return "kucuk20";
        return "";
    };

    const getMevkiAtt = (posAtt) => {
        if (posAtt <= 0.6) return "bosmevki";
        if (posAtt <= 0.95) return "yanmevki";
        if (posAtt <= 1) return "anamevki";
        return "bosmevki";
    };



    if (!player.name || !player.playerAttributes || !player.playerPositions) {
        return <p>Loading...</p>;
    } else {
        return (
            <div className='backOnePlayer ' style={{ height: "690px" }}>
                <div className='row'>
                    <div className='col-4'>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-4'>
                                <img className="card-img-top" src={playerImaages(1)} alt={player.id} style={{ width: "150px" }} />
                            </div>
                            <div className='col-1'></div>
                            <div className='col-6'>
                                <div className='textoneplayer mt-3'>{player.name + " " + player.surname}</div>
                                <div className='textoneplayer'>{player.agePlayer}</div>
                            </div>
                        </div>
                    </div>
                    <div className='col-1'></div>
                    <div className='col-3 mt-3'>
                        <div className='textoneplayer'>{player.strong}</div>
                        <div className='textoneplayer'>{player.value}</div>
                    </div>
                    <div className='col-1'></div>
                    <div className='col-3 mt-3'>
                        <div className='textoneplayer'>{player.team2}</div>
                        <div className='textoneplayer'>{player.country}</div>
                    </div>
                </div>
                <div className='row'>
                    <div className='col-3' >
                        <div className='d-flex flex-column align-items-center mt-4'>
                            <div className={`pozcircle gkmevki ${getMevkiAtt(player.playerPositions.gk)}`}></div>
                            <div className={`pozcircle dcmevki ${getMevkiAtt(player.playerPositions.dc)}`}></div>
                            <div className={`pozcircle drmevki ${getMevkiAtt(player.playerPositions.dr)}`}></div>
                            <div className={`pozcircle dlmevki ${getMevkiAtt(player.playerPositions.dl)}`}></div>
                            <div className={`pozcircle dmmevki ${getMevkiAtt(player.playerPositions.dm)}`}></div>
                            <div className={`pozcircle mcmevki ${getMevkiAtt(player.playerPositions.mc)}`}></div>
                            <div className={`pozcircle mrmevki ${getMevkiAtt(player.playerPositions.mr)}`}></div>
                            <div className={`pozcircle mlmevki ${getMevkiAtt(player.playerPositions.ml)}`}></div>
                            <div className={`pozcircle amcmevki ${getMevkiAtt(player.playerPositions.amc)}`}></div>
                            <div className={`pozcircle amrmevki ${getMevkiAtt(player.playerPositions.amr)}`}></div>
                            <div className={`pozcircle stmevki ${getMevkiAtt(player.playerPositions.st)}`}></div>
                            <div className={`pozcircle amlmevki ${getMevkiAtt(player.playerPositions.aml)}`}></div>

                            <img className="card-img-top" src="/images/other/pozisyon.png" alt={player.id} style={{ width: "300px" }} />
                        </div>
                        <div className='textoneplayer'>{player.position}</div>
                    </div>
                    <div className='col-3'>
                        <RadarChart attributes={attributes} />
                    </div>
                    <div className='col-2'>
                        <br />
                        <div className='row'></div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Bitiricilik</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.bitiricilik))}`}
                            ><p>{Math.round(player.playerAttributes.bitiricilik)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Şut</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.sut))}`}
                            ><p>{Math.round(player.playerAttributes.sut)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Plase</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.plase))}`}
                            ><p>{Math.round(player.playerAttributes.plase)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Orta</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.orta))}`}
                            ><p>{Math.round(player.playerAttributes.orta)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Pas</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.pas))}`}
                            ><p>{Math.round(player.playerAttributes.pas)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Görüş</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.gorus))}`}
                            ><p>{Math.round(player.playerAttributes.gorus)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Yetenek</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.yetenek))}`}
                            ><p>{Math.round(player.playerAttributes.yetenek)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Falso</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.falso))}`}
                            ><p>{Math.round(player.playerAttributes.falso)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Top Kontrolü</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.topKontrolu))}`}
                            ><p>{Math.round(player.playerAttributes.topKontrolu)}</p></div>
                        </div>
                    </div>
                    <div className='col-2'>
                        <br />
                        <div className='row'></div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Hız</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.hiz))}`}
                            ><p>{Math.round(player.playerAttributes.hiz)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Ani Hız</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.aniHiz))}`}
                            ><p>{Math.round(player.playerAttributes.aniHiz)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Dripling</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.dripling))}`}
                            ><p>{Math.round(player.playerAttributes.dripling)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Kafa İsabeti</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.kafaIsabeti))}`}
                            ><p>{Math.round(player.playerAttributes.kafaIsabeti)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Kafa Vuruşu</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.kafaVurusu))}`}
                            ><p>{Math.round(player.playerAttributes.kafaVurusu)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Zıplama</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.ziplama))}`}
                            ><p>{Math.round(player.playerAttributes.ziplama)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Güç</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.guc))}`}
                            ><p>{Math.round(player.playerAttributes.guc)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Dayanıklılık</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.dayaniklilik))}`}
                            ><p>{Math.round(player.playerAttributes.dayaniklilik)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Çeviklik</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.ceviklik))}`}
                            ><p>{Math.round(player.playerAttributes.ceviklik)}</p></div>
                        </div>
                    </div>
                    <div className='col-2'>
                        <br />
                        <div className='row'></div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Top Kesme</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.topKesme))}`}
                            ><p>{Math.round(player.playerAttributes.topKesme)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Top Kapma</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.topKapma))}`}
                            ><p>{Math.round(player.playerAttributes.topKapma)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Markaj</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.markaj))}`}
                            ><p>{Math.round(player.playerAttributes.markaj)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Liderlik</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.liderlik))}`}
                            ><p>{Math.round(player.playerAttributes.liderlik)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Soğukkanlılık</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.sogukkanlilik))}`}
                            ><p>{Math.round(player.playerAttributes.sogukkanlilik)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-8 textoneplayer'><p>Kararlılık</p></div>
                            <div className={`col-2 textoneplayer text-end ${getClassName(Math.round(player.playerAttributes.kararlilik))}`}
                            ><p>{Math.round(player.playerAttributes.kararlilik)}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-7 textoneplayer'><p>Kullandığı Ayak</p></div>
                            <div className='col-3 textoneplayer text-end'><p>Sol</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-7 textoneplayer'><p>Ağırlık</p></div>
                            <div className='col-3 textoneplayer text-end'><p>{player.agirlik}</p></div>
                        </div>
                        <div className='row'>
                            <div className='col-1'></div>
                            <div className='col-7 textoneplayer'><p>Boy</p></div>
                            <div className='col-3 textoneplayer text-end'><p>{player.boy}</p></div>
                        </div>

                    </div>
                </div>

            </div>
        )
    }


}
