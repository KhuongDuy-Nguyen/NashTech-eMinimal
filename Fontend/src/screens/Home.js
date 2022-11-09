import Navbar from "../components/home/Navbar";
import Banner from "../components/home/Banner";
import Footer from "../components/home/Footer";
import Product from "../components/home/Product";
import Policy from "../components/home/Policy";
import Category from "../components/home/Category";

// import "../styles/home.module.css";

import { Container } from "react-bootstrap";
import ProductTitle from "../components/home/ProductTitle";

function Home() {
  return (
    <div className="container-fluid p-0">
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
