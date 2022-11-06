import { message } from "antd";

export default function ErrorAuth(err){
    if(err.response.status === 401){
        message.error(
          "You are not authorized to access this page. Try login again."
        );
        localStorage.removeItem("token");
        setTimeout(() => {
            window.location.href = "/login";
        }, 1000);
    }else{
        console.log(err);
        message.error(err.response.data.message);
    }
}