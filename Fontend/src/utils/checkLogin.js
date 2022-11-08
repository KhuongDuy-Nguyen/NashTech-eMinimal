import ShowMessage from "./message";


const checkLogin = () =>{
    const token = localStorage.getItem('token');
    if(token){
        return true;
    }
    ShowMessage("error","Please login to continue");
    return false;
}

export default checkLogin;