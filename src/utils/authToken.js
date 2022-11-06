import axios from "axios";

export default function authToken(token){
    if(token){
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        // console.log("SETUP COMPLETE ------> " + axios.defaults.headers.common['Authorization']);
    }
    else
        delete axios.defaults.headers.common['Authorization'];
}