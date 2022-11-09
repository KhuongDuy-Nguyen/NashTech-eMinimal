import logout from "./logout";
import ShowMessage from "./message";

function ErrorAuth(err) {
  console.log(err);
  if (err.response.status === 401) {
    ShowMessage("error","You are not authorized to access this page. Try login again.");
    localStorage.removeItem("token");
    setTimeout(() => {
      logout();
    }, 1000);
  } else {
    console.log("Run ErrorAuth");
    console.log(err);
    ShowMessage("error",err.response.data.message);
  }
}

export default ErrorAuth;
