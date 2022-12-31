import React, { createContext, useReducer, Dispatch, useInsertionEffect, useEffect} from "react"
import {DateRange} from 'react-date-range';
import { addDays } from "date-fns";
import { actionType } from "../dataTypes/ContextTypes";
import { authState } from "../dataTypes/ContextTypes";

type authContextProviderProps = {
    children: React.ReactNode;
}
const INITIAL_STATE = {
    user: JSON.parse(localStorage.getItem("user")!),
    loading: false,
    error: null,
}

export const AuthContext = createContext<{
    state:authState, 
    dispatch:Dispatch<actionType>}>
({
    state:INITIAL_STATE,
    dispatch: () => null
});

const authReducer = (state:authState, action: actionType) => {
    switch(action.type) {
        case "LOGIN_START":
            return {
                    user: null,
                    loading: true,
                    error: null,
                }
            
        case "LOGIN_SUCCESS":
            return {

                    user: action.payload,
                    loading: false,
                    error: null,
            };
        case "LOGIN_FAILURE" : {
            return {
                user: null,
                loading: false,
                error: action.payload
            }
        }
        case "LOGOUT":
            return {
                user: null,
                loading: false,
                error: null
            }
        default:
            return state;
    }
}


export const AuthContextProvider = ({children}:authContextProviderProps) => {
    const [state, dispatch] = useReducer(authReducer, INITIAL_STATE)

    useEffect(()=>{
        localStorage.setItem("user", JSON.stringify(state.user))
    })

    return (
        <AuthContext.Provider value={{state, dispatch}}>
            {children}
        </AuthContext.Provider>
    )
}