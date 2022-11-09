import page from "../assets/images/401.jpg"

export default function App(){

    setTimeout(() => {
      window.location.href = "/auth";
    }, 2000);

    return (
      <img
        src={page}
        alt="404 Not Found"
        style={{
          width: "100%",
          height: "100%",
          position: "absolute",
        }}
      />
      
    );
}