import { createSlice } from "@reduxjs/toolkit"

const initialState = {
    localhost: "http://192.168.1.44:8080/api/"
}


export const degiskenSlice = createSlice({
    name: "degiskenSlice",
    initialState,
    reducer: {
    },
})

export const { } = degiskenSlice.actions;
export default degiskenSlice.reducer;