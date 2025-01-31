import { createSlice, createAsyncThunk } from "@reduxjs/toolkit"
import axios from "axios"
import { useSelector } from "react-redux";

const initialState = {
    // leagues: [],
    leagues2: [],
    loading: false
}


export const LeagueAllThunk = createAsyncThunk("name2", async () => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    console.log(localhost)
    const response = await axios.get(localhost + "league/all")
    return response.data;
})

export const leagueSlice = createSlice({
    name: "leaugeSliceName",
    initialState,
    reducer: {

    },
    extraReducers: (builderPrm) => {
        // builderPrm.addCase(LeagueThunk.fulfilled, (state, action) => {
        //     state.leagues = action.payload;
        // })
        builderPrm.addCase(LeagueAllThunk.fulfilled, (state, action) => {
            state.leagues2 = action.payload;
        })
    }
})

export const { } = leagueSlice.actions;
export default leagueSlice.reducer;