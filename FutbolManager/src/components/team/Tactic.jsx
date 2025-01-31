import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { useDrag, useDrop, DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { useSelector } from "react-redux";

const ItemType = 'PLAYER_SLOT';

const SlotSaha = ({ player, role, onDrop }) => {
    const [{ isDragging }, drag] = useDrag(() => ({ // bu sadece oyuncu tutulduğunda renk değiştirmke için
        type: ItemType,
        item: { role },
        collect: (monitor) => ({
            isDragging: monitor.isDragging(),
        }),
    }));

    const [{ isOver }, drop] = useDrop(() => ({ // buda move hareketi ile alakalı
        accept: ItemType,
        drop: (item) => onDrop(item.role, role),
        collect: (monitor) => ({
            isOver: monitor.isOver(),
        }),
    }));

    const positionClass =
        role === 'ST' || role === 'AMR' || role === 'AML' ? 'attackers' :
            role === 'AMC' || role === 'MCR' || role === 'MCL' ? 'middfielders' :
                role === 'DL' || role === 'DCR' || role === 'DCL' || role === 'DR' ? 'deffenders' :
                    role === 'GK' ? 'goalkeppers' : '';

    const positionClass2 =
        role === 'ST' || role === 'AMR' || role === 'AML' ? 'attacker' :
            role === 'AMC' || role === 'MCR' || role === 'MCL' ? 'middfielder' :
                role === 'DL' || role === 'DCR' || role === 'DCL' || role === 'DR' ? 'deffender' :
                    role === 'GK' ? 'goalkepper' : '';

    const posMargin = role === "ST" ? "st_position" : role === "AMR" ? "amr_position" :
        role === "AMC" ? "amc_position" : role === "AML" ? "aml_position" : role === "MCR" ? "mc_position" :
            role === "MCL" ? "dm_position" : role === "DR" ? "dr_position" : role === "DL" ? "dl_position" :
                role === "DCL" ? "dcl_position" : role === "DCR" ? "dcr_position" : role === "GK" ? "gk_position" : "non";

    return (
        <div className={`d-flex flex-column taktics-card  ${posMargin}`}
            ref={(node) => drag(drop(node))}
            style={{
                cursor: 'move',
                opacity: isDragging ? 0.2 : 1, // Drag sırasında saydamlık
            }}
        >
            <div style={{
                width: "110px",
                backgroundColor: isOver ? 'rgba(88, 242, 88, 0.71)' : isDragging ? 'rgba(245, 81, 69, 0.1)' : 'rgba(207, 239, 220, 0.3)',
            }} className='row border border-dark'>
                <div className='col-6'>
                    <img className='' src="/images/other/head.png" alt="" />
                </div>
                <div className='col-6'>
                    <div className={`textTactik ${positionClass2}`}>{role} { }</div>
                    <div className={`textTactik`}>{player ? `${player.strong}` : ''}</div>
                </div>
            </div>

            <div style={{ width: "110px" }} className={`border border-dark card-text text-center textTactik ${positionClass}`}> {player ? (player.surname.trim().length > 1 ? `${player.name.slice(0, 1)}. ${player.surname}` : player.name) : '-'}</div>
        </div>
    );
};

const SlotYedek = ({ player, role, onDrop }) => {
    const [{ isDragging }, drag] = useDrag(() => ({ // bu sadece oyuncu tutulduğunda renk değiştirmke için
        type: ItemType,
        item: { role },
        collect: (monitor) => ({
            isDragging: monitor.isDragging(),
        }),
    }));

    const [{ isOver }, drop] = useDrop(() => ({ // buda move hareketi ile alakalı
        accept: ItemType,
        drop: (item) => onDrop(item.role, role),
        collect: (monitor) => ({
            isOver: monitor.isOver(),
        }),
    }));

    return (
        <div className='row border border-dark ms-4'
            ref={(node) => drag(drop(node))}
            style={{
                backgroundColor: isOver ? 'rgba(88, 242, 88, 0.71)' : isDragging ? 'rgba(4, 31, 4, 0.1)' : 'rgba(34, 249, 67, 0.16)',
                cursor: 'move',
                opacity: isDragging ? 0.3 : 1, // Drag sırasında saydamlık
            }}
        >
            <div className='whiteText col border border-success'>{role}</div>
            <div className='whiteText col-5 border border-success'>{player ? `${player.name} ${player.surname}` : ''}</div>
            <div className='whiteText col border border-success'>{player ? `${player.position}` : ''}</div>
            <div className='whiteText col border border-success text-center'>{player ? `${player.strong}` : ''}</div>
            <div className='whiteText col border border-success text-end'>{player ? `%100` : ''}</div>
        </div>
    );
};

const OneTeam = () => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const { teamProp } = useParams();
    const [players, setPlayers] = useState([]);
    const formationRoles = ["GK", "DCR", "DCL", "DR", "DL", "MCL", "MCR", "AMC", "AMR", "AML", "ST"];
    const benchRoles = ["Y1", "Y2", "Y3", "Y4", "Y5", "Y6", "Y7", "Y8", "Y9", "Y10", "Y11"];

    const onbir = [];
    const kdSay = [];

    const [kdEkle, setKdEkle] = useState([]);


    useEffect(() => {
        const fetchPlayers = async () => {
            const response = await axios.get(localhost + 'player/playerthisteam', {
                params: { team2: teamProp },
            });
            setPlayers(response.data);
        };
        fetchPlayers();
    }, [teamProp]);

    useEffect(() => {
        setKdEkle([])
        players.filter(player => player.squad > 22).map(item => {
            const sira = item.squad - 22
            setKdEkle((prev) => [...prev, "KD" + sira]);
        })
    }, [players])

    const handleDrop = (fromRole, toRole) => {
        setPlayers((prevPlayers) => {
            const updatedPlayers = [...prevPlayers];
            const fromIndex = updatedPlayers.findIndex(p => p.role === fromRole);
            const toIndex = updatedPlayers.findIndex(p => p.role === toRole);
            if (fromIndex !== -1 && toIndex !== -1) {
                [updatedPlayers[fromIndex].role, updatedPlayers[toIndex].role] = [updatedPlayers[toIndex].role, updatedPlayers[fromIndex].role];
            } else if (fromIndex !== -1) {
                updatedPlayers[fromIndex].role = toRole;
            }
            return updatedPlayers;
        });
    };

    const slotsRenderSaha = (roles) => {
        return roles.map(role => {
            const player = players.find(p => p.role === role);
            return <SlotSaha key={role} player={player} role={role} onDrop={handleDrop} />;
        });
    };

    const slotsRenderYedek = (roles) => {
        return roles.map(role => {
            const player = players.find(p => p.role === role);
            return <SlotYedek key={role} player={player} role={role} onDrop={handleDrop} />;
        });
    };

    const handleSave = () => {
        console.log('Güncellenmiş Players Dizisi:', players);
    };

    return (
        <DndProvider backend={HTML5Backend}>
            <div className='back'>
                <div className="row m-1">
                    <div className="saha mt-5" style={{ width: "725px", height: "500px", position: "relative" }}>
                        <div className="">{slotsRenderSaha(formationRoles)}</div>
                        <button className='btn btn-outline-success'
                            onClick={handleSave} style={{ float: 'right', marginTop: "15px" }}>Kaydet
                        </button>
                    </div>
                    <div className="col mt-5 me-4">
                        <div className='row mt-2 ms-4 yellowText'>
                            <div className='col border border-success'>Role</div>
                            <div className='col-5 border border-success'>Yedek Kadro</div>
                            <div className='col border border-success'>Poz</div>
                            <div className='col border text-center border-success'>Str</div>
                            <div className='col border text-end border-success'>Kond</div>
                        </div>
                        {slotsRenderYedek(benchRoles)}

                        <div className='row mt-2 ms-4 yellowText'>
                            <div className='col border border-success'>Role</div>
                            <div className='col-5 border border-success'>Kadro Dışı</div>
                            <div className='col border border-success'>Poz</div>
                            <div className='col border text-center border-success'>Str</div>
                            <div className='col border text-end border-success'>Kond</div>
                        </div>
                        {slotsRenderYedek(kdEkle)}
                    </div>
                </div>
            </div>
        </DndProvider>
    );
};

export default OneTeam;