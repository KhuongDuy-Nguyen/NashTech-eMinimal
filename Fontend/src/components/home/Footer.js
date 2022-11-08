import React from "react"
import { Col, Row } from "react-bootstrap"
import style from "../../styles/footer.module.css"

function Footer(){
    return (
      <footer>
        <Row className="row p-5 bg-dark">
          <Col className="col-3 p-4 text-white" id="about">
            <h5 className="text-uppercase text-white">About the store</h5>
            <hr />
            <div id="info_about" className="text-white-50">
              <p className={style.info_about_p}>
                eNiture - worldwide furniture store since 1950. We sell over
                1000+ branded products on out web-site
              </p>
              <p>
                <i className="fa-sharp fa-solid fa-location-dot me-3"></i>123
                Street, New York, USA
              </p>
              <p>
                <i className="fa-solid fa-phone me-3"></i>Phone:(123) 456-8900
              </p>
            </div>
          </Col>

          <Col className="col-3 p-4 text-white" id="service">
            <h5 className="text-uppercase text-white">Service</h5>
            <hr />
            <div id="info_service" className="text-white-50">
              <p>Shipping & Return</p>
              <p>Gift Cards</p>
              <p>Track My Order</p>
              <p>Terms % Policy</p>
            </div>
          </Col>

          <Col className="col-3 p-4 text-white" id="account">
            <h5 className="text-uppercase text-white">Account</h5>
            <hr />
            <div id="info_account" className="text-white-50">
              <p>My Account</p>
              <p>Checkout</p>
              <p>My Cart</p>
            </div>
          </Col>

          <Col className="col-3 p-4 text-white" id="connect">
            <h5 className="text-uppercase text-white">Connect to us</h5>
            <hr />
            <div id="info_connect">
              <a className={style.connect} href="https://facebook.com">
                <i className="fa-brands fa-square-facebook fa-2xl"></i>
              </a>
              <a className={style.connect} href="https://twitter.com">
                <i className="fa-brands fa-square-twitter fa-2xl"></i>
              </a>
              <a className={style.connect} href="https://instagram.com">
                <i className="fa-brands fa-instagram fa-2xl"></i>
              </a>
            </div>
          </Col>
        </Row>

        <div id="license" className="text-center bg-dark bg-opacity-75 p-2">
          <h5 className="p-0 m-0 text-white-50">
            © 2021 eNiture. All rights reserved
          </h5>
        </div>
      </footer>
    );
}

export default Footer