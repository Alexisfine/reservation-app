import React from "react";
import { IUser } from "./ApiTypes";

export interface searchState {
    city: string | undefined,
    date: (Date)[],
    options: {
        adults: number| undefined,
        children: number | undefined,
        roomNumber: number |undefined
    }
}

export interface authState {
    user: null | IUser
    loading: boolean,
    error: any
}

export type actionType = {
    type: string,
    payload?: any,
} 

