import Home from "./pages/Home"
import Team from "./pages/Team"
import Player from "./pages/Player"
import League from "./pages/League"
import NotFound from "./pages/NotFound"
import MyNavbar from "./pages/MyNavbar"
import { BrowserRouter as Router, Routes, Route, useParams, Link } from "react-router-dom"
import AllLeague from "./components/league/AllLeague"
import AllTeam from "./components/team/AllTeam"
import AllPlayer from "./components/player/AllPlayer"
import "./css/app.css"
import "./css/OneLeague.css"
import "./css/OneTeam.css"
import HomeLeague from "./components/league/HomeLeague"
import Tactic from "./components/team/Tactic"
import HomeTeam from "./components/team/HomeTeam"
import Squad from "./components/team/Squad"
import OnePlayer from "./components/player/OnePlayer"
import "./css/OnePlayer.css"
import "./css/teamsidebar.css"
import { useEffect, useState } from "react"
import Fixture from "./components/league/Fixture"
import LeaderboardLeague from "./components/league/LeaderboardLeague"
import Scored from "./components/league/Scored"

const TeamLayout = () => {
  const { teamProp } = useParams();
  const [isSmallScreen, setIsSmallScreen] = useState(window.innerWidth < 992);

  useEffect(() => {
    const handleResize = () => {
      setIsSmallScreen(window.innerWidth < 992);
    };
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return (
    <div className="row">
      <div className={`col-10 col-lg-2 ${isSmallScreen ? "col-12 mobile-sidebar" : "sidebar"}`}>
        <Link to={`/team/${teamProp}`}>Home</Link>
        <Link to={`/team/${teamProp}/tactic`}>Tactic</Link>
        <Link to={`/team/${teamProp}/squad`}>Squad</Link>
      </div>
      <div className="col-10">
        <Routes>
          <Route path="/" element={<HomeTeam />} />
          <Route path="/tactic" element={<Tactic />} />
          <Route path="/squad" element={<Squad />} />
        </Routes>
      </div>
    </div>
  );
};

const LeagueLayout = () => {
  const { leagueProp } = useParams();
  const [isSmallScreen, setIsSmallScreen] = useState(window.innerWidth < 992);

  useEffect(() => {
    const handleResize = () => {
      setIsSmallScreen(window.innerWidth < 992);
    };
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return (
    <div className="row">
      <div className={`col-10 col-lg-2 ${isSmallScreen ? "col-12 mobile-sidebar" : "sidebar"}`}>
        <Link to={`/league/${leagueProp}`}>Home</Link>
        <Link to={`/league/${leagueProp}/fixture`}>Fixture</Link>
        <Link to={`/league/${leagueProp}/leaderboard`}>Puan Tablosu</Link>
        <Link to={`/league/${leagueProp}/scored`}>Gol Krallığı</Link>
      </div>
      <div className="col-10">
        <Routes>
          <Route path="/" element={<HomeLeague />} />
          <Route path="/fixture" element={<Fixture />} />
          <Route path="/leaderboard" element={<LeaderboardLeague />} />
          <Route path="/scored" element={<Scored />} />
        </Routes>
      </div>
    </div>
  );
};





function App() {



  return (
    <div>


      <MyNavbar></MyNavbar>
      <Routes>

        <Route path="/" element={<Home></Home>}></Route>

        <Route path="/league" element={<League></League>}></Route>
        <Route path="/league/:leagueProp/*" element={<LeagueLayout ></LeagueLayout>}></Route>
        <Route path="/league/allleague" element={<AllLeague></AllLeague>}></Route>


        <Route path="/team" element={<Team></Team>}></Route>
        <Route path="/team/:teamProp/*" element={<TeamLayout ></TeamLayout>}></Route>
        <Route path="/team/allteam" element={<AllTeam></AllTeam>}></Route>


        <Route path="/player" element={<Player></Player>}></Route>
        <Route path="/player/allplayer" element={<AllPlayer></AllPlayer>}></Route>
        <Route path="/player/:playerProp" element={<OnePlayer></OnePlayer>}></Route>

        <Route path="/*" element={<NotFound></NotFound>}></Route>

      </Routes>

    </div>
  )
}

export default App
