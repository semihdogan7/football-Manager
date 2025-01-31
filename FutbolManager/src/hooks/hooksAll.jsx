import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
// import { LeagueThunk } from "../redux/leagueSlice";
import { getAllTeamsAPI, TeamsOneLeagueThunk } from "../redux/teamSlice";
import { LeagueAllThunk } from "../redux/leagueSlice";
import { myDataThunk } from "../redux/myDataSlice";
import { getAllPlayersAPI } from "../redux/playerSlice"
import { playerPageThunk } from "../redux/playerSlice";


//* Ligleri team ve playerler ile beraber aktar
// export const allLeaugesHooks = () => {
//     const dispatch = useDispatch();
//     const { leagues, loading } = useSelector((state) => state.getLeagueStore);

//     useEffect(() => {
//         // Eğer kullanıcı verileri yüklenmediyse, API çağrısı yap
//         if (leagues.length === 0) {
//             dispatch(LeagueThunk());
//         }
//     }, [dispatch, leagues]);

//     return { leagues, loading };
// };

//*  Ligleri aktar
export const LeagueAllHooks = () => {
    const dispatch = useDispatch();
    const { leagues2, loading } = useSelector((state) => state.LeagueStore);

    useEffect(() => {
        // Eğer kullanıcı verileri yüklenmediyse, API çağrısı yap
        if (leagues2.length === 0) {
            dispatch(LeagueAllThunk());
        }
    }, [dispatch, leagues2]);

    return { leagues2, loading };
};

//* Sadece tek bir ligi aktar
export const teamsOneLeagueHooks = (league) => {
    const dispatch = useDispatch();
    const { oneloague, loading } = useSelector((state) => state.getTeamStore);

    useEffect(() => {
        // Eğer kullanıcı verileri yüklenmediyse, API çağrısı yap
        if (oneloague.length === 0) {
            dispatch(TeamsOneLeagueThunk(league));
        }
    }, [dispatch, oneloague]);

    return { oneloague, loading };
};

//* Takımları aktar
export const allTeamsHooks = () => {
    const dispatch = useDispatch();
    const { teams, loading } = useSelector((state) => state.getTeamStore);

    useEffect(() => {
        if (teams.length === 0) {
            dispatch(getAllTeamsAPI());
        }
    }, [dispatch], teams);

    return { teams, loading };
}

//* Futbolcular
export const allPlayersHooks = () => {
    const dispatch = useDispatch();
    const { players, loading } = useSelector((state) => state.getPlayerStore);

    useEffect(() => {
        if (players.length === 0) {
            dispatch(getAllPlayersAPI());
        }
    }, [dispatch], players);
    return { players, loading };
}

//* Futbolcular Pages
export const playerPageHooks = ({ page, size }) => {
    const dispatch = useDispatch();
    const { playerPage, pagesSize } = useSelector((state) => state.getPlayerStore);
    useEffect(() => {
        if (playerPage.length === 0) {
            dispatch(playerPageThunk({ page, size }));
        }
    }, [dispatch], playerPage);
    return { playerPage, pagesSize };
}

//* MyData
export const myDataHooks = () => {
    const dispatch = useDispatch();
    const { mydatas, loading } = useSelector((state) => state.getMyDataStore);

    useEffect(() => {
        if (mydatas.length === 0) {
            dispatch(myDataThunk());
        }
    }, [dispatch], mydatas);
    return { mydatas, loading };
}
