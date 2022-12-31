import axios, { AxiosError } from 'axios';
import { previousDay } from 'date-fns';
import React, { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/authContext'
import "./Login.scss"

const Login = () => {
    const [credentials, setCredentials] = useState({
        username: undefined,
        password: undefined,
    })

    const {state, dispatch} = useContext(AuthContext);
    const loading = state.loading;
    const error = state.error;

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCredentials(prev=>({...prev, [e.target.id]:e.target.value}))
    }

    const handleLogin = async (e:React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        e.preventDefault();
        dispatch({type:"LOGIN_START"});
        try {
            const res = await axios.post("/auth/v1/login", credentials)
            dispatch({type:"LOGIN_SUCCESS", payload:res.data.data.user.user})
            navigate("/")

        } catch(err) {
            dispatch({type:"LOGIN_FAILURE", payload:err})
        }


    }

    const navigate = useNavigate();

  return (
    <div className='login'>
        <div className="loginContainer">
            <input type="text" className="input" placeholder='username' id="username" 
                onChange={(e) => handleChange(e)}/>
             <input type="password" className="input" placeholder='password' id="password" 
                onChange={(e) => handleChange(e)}/>   
            <button disabled={loading} className="button" onClick={(e) => handleLogin(e)}>Login</button>
            {error && <span>"Error"</span>}
        </div>
    </div>
  )
}

export default Login