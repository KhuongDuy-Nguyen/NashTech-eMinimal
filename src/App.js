
import Login from "./screens/Login";
import Home from "./screens/Home";

import { Route, Routes } from "react-router-dom";
import React, { useEffect } from "react";

import "mdb-react-ui-kit/dist/css/mdb.min.css";

function App() {

  useEffect(() => {
    
  }, []);

  return (
    <div className="App">
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route path="/category/:categoryName" element={<Home />} />
        <Route path="/product/:filter" element={<Home />} />

        <Route path="/login" element={<Login />} />
      </Routes>
    </div>
  );
}

export default App;
