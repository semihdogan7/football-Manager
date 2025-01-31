import { createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios"
import { useSelector } from "react-redux";

const initialState = {
    teams: [],
    oneloague: [],
    loading: false
}

export const getAllTeamsAPI = createAsyncThunk("teamsPrm", async () => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const response = await axios.get(localhost + "team/all");
    return response.data;
})

export const TeamsOneLeagueThunk = createAsyncThunk("thunk2", async (league) => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const response = await axios.get(`${localhost}team/oneleague/${league}`)
    return response.data;
})

export const teamSlice = createSlice({
    name: "teamsSliceName",
    initialState,
    reducer: {

    },
    extraReducers: (builder) => {
        builder.addCase(getAllTeamsAPI.fulfilled, (state, action) => {
            state.teams = action.payload;
        })
        builder.addCase(TeamsOneLeagueThunk.fulfilled, (state, action) => {
            state.oneloague = action.payload;
        })
    }
})

export const { } = teamSlice.actions;
export default teamSlice.reducer;