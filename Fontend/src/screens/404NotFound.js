import page from "../assets/images/404.gif";


function App() {

  return (
    <div className="App">
      <img src={page} alt="404" style={{
        width: "100%",
        height: "100%",
        position: "absolute"
      }}   
      />
    </div>
  );
}

export default App;
