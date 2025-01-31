import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { useSelector } from "react-redux";

const initialState = {
    mydatas: [],
    loading: false
}

export const myDataThunk = createAsyncThunk("Thunk 1", async () => {
    const { localhost } = useSelector(store => store.getDegiskenler)
    console.log(localhost)
    const response = await axios.get(localhost + "database/mydata");
    return response.data;
})

export const myDataSlice = createSlice({
    name: "myData",
    initialState,
    reducer: {

    },
    extraReducers: (builder) => {
        builder.addCase(myDataThunk.fulfilled, (state, action) => {
            state.mydatas = action.payload;
        })
    }
})

export const { } = myDataSlice.actions;
export default myDataSlice.reducer;