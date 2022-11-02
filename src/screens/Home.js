import Navbar from "../components/Navbar";
import Banner from "../components/Banner";
import Footer from "../components/Footer";
import Product from "../components/Product";
import Policy from "../components/Policy";
import Category from "../components/Category";

// import "../styles/home.module.css";

import { Container } from "react-bootstrap";
import ProductTitle from "../components/ProductTitle";
import { useEffect, useState } from "react";

function Home() {
  const [token, setToken] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token");
    setToken(token);
    
  }, []);



  return (
    <div className="container-fluid p-0">
      {console.log(token)}
      <Navbar />
      <Banner />
      <Container>
        <Policy />
        <Category />
        <ProductTitle />
        <Product />
      </Container>
      <Footer />
    </div>
  );
}

export default Home;
