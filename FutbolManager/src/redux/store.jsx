import { configureStore } from "@reduxjs/toolkit"
import leagueSlice from "./leagueSlice"
import teamSlice from "./teamSlice"
import playerSlice from "./playerSlice"
import myDataSlice from "./myDataSlice"
import degiskenSlice from "./degiskenSlice"


export const store = configureStore({
    reducer: {
        LeagueStore: leagueSlice,
        getTeamStore: teamSlice,
        getPlayerStore: playerSlice,
        getMyDataStore: myDataSlice,
        getDegiskenler: degiskenSlice,
    },
})