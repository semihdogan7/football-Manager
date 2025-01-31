import { createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios"
import { useSelector } from "react-redux";

const initialState = {
    players: [],
    playerPage: [],
    pagesSize: 0,
    loading: false
}

export const getAllPlayersAPI = createAsyncThunk("playerThunkName", async () => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const response = await axios.get(localhost + "player/all");
    return response.data;
})

export const playerPageThunk = createAsyncThunk("player2", async ({ page, size }) => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    const response = await axios.get(`${localhost}player/playerpage?page=${page}&size=${size}`);
    return response.data;
})

export const playerSlice = createSlice({
    name: "playerSliceName",
    initialState,
    reducer: {},
    extraReducers: (builder) => {
        builder.addCase(getAllPlayersAPI.fulfilled, (state, action) => {
            state.players = action.payload;
        })
        builder.addCase(playerPageThunk.fulfilled, (state, action) => {
            state.playerPage = action.payload.content;
            state.pagesSize = action.payload.totalPages;
        })
    }
})

export const { } = playerSlice.actions;
export default playerSlice.reducer;