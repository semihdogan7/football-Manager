import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from "react-redux";

export default function HomeLeague() {
  const { localhost } = useSelector(store => store.getDegiskenler)
  const ImagesTeam = (id) => `/images/team/${id}.png`;
  const ImagesPlayer = (id) => `/images/player/player (${id}).png`;

  const { leagueProp } = useParams();
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    getTeams();
  }, [leagueProp]);

  const getTeams = async () => {
    const response = await axios.get(`${localhost}team/oneleague`, {
      params: { league: leagueProp }
    });
    setTeams(response.data);
  };

  return (
    <div>
      <div className='col-8 my-3'>
        <div className='card p-3 shadow'>
          <h4 className='text-center'>{leagueProp}</h4>
          <div>
            <div className='row d-flex justify-content-between border-bottom py-2'>
              <div className='col text-end'>#</div>
              <div className='col-3 '>Takım</div>
              <div className='col text-center'>Oyuncu</div>
              <div className='col text-end'>Değer</div>
              <div className='col text-center'>Scout</div>
              <div className='col text-center'>İtbr</div>
              <div className='col text-center'>Antr</div>
              <div className='col text-center'>Altyp</div>
              <div className='col text-end'>Tarft</div>
            </div>
          </div>
          <div className='card-body'>
            {
              teams.map((team, index) => (
                <div style={{ height: "35px" }} className='row d-flex justify-content-between border-bottom py-2' key={team.id}>
                  <div className='col text-fixture text-end'>{index + 1}</div>
                  <div className='col-3 text-fixture'>
                    <div className='row'>
                      <div className='col-2'>
                        <img className="card-img-top " src={ImagesTeam(team.name)} alt={team.id} style={{ width: "25px", paddingBottom: "20px" }} />
                      </div>
                      <div className='col '>
                        <Link to={"/team/" + team.name} target="_blank" >{team.name}</Link>
                      </div>
                    </div>
                  </div>
                  <div className='col text-fixture text-center'>{team.playerCount}</div>
                  <div className='col text-end text-fixture'>
                    {team.totalValue.toLocaleString('tr-TR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })} M €
                  </div>
                  <div className='col text-fixture text-center'>{team.scout}</div>
                  <div className='col text-fixture text-center'>{team.itibar}</div>
                  <div className='col text-fixture text-center'>{team.antreman}</div>
                  <div className='col text-fixture text-center'>{team.altyapi}</div>
                  <div className='col text-fixture text-end'>{team.taraftar / 1000} bin</div>
                </div>
              ))
            }
          </div>
        </div>
      </div>
    </div>
  )
}
