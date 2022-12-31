import React, { createContext, useReducer, Dispatch} from "react"
import { actionType, searchState } from "../dataTypes/ContextTypes";
import {DateRange} from 'react-date-range';
import { addDays } from "date-fns";


type searchContextProviderProps = {
    children: React.ReactNode;
}
const INITIAL_STATE = {
    city: undefined,
    date: [addDays(Date.now(),0), addDays(Date.now(), 3)],
    options: {
        adults: 1,
        children: 0,
        roomNumber: 1
    }
}

export const SearchContext = createContext<{
    state:searchState, 
    dispatch:Dispatch<actionType>}>
({
    state:INITIAL_STATE,
    dispatch: () => null
});

const searchReducer = (state:searchState, action: actionType) => {
    switch(action.type) {
        case "NEW_SEARCH":
            return action.payload;
        case "RESET_SEARCH":
            return INITIAL_STATE;
        default:
            return state;
    }
}

export const SearchContextProvider = ({children}:searchContextProviderProps) => {
    const [state, dispatch] = useReducer(searchReducer, INITIAL_STATE)

    return (
        <SearchContext.Provider value={{state, dispatch}}>
            {children}
        </SearchContext.Provider>
    )
}