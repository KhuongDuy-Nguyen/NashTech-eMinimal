import React from "react";
import { Nav, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import style from "../../styles/productTitle.module.css"

function ProductTitle() {
  return (
    <div id="product_title" className="mb-5">
      <h3 className={style.product_title_h3} id="section_shop">
        - out products -
      </h3>

      <Row>
        <Nav className="nav col-8" id="nav_product">
          <Nav.Link>
            <Nav.Item>
              <Link to="/product/all" className={style.product_title}>
                All
              </Link>
            </Nav.Item>
          </Nav.Link>

          <Nav.Link>
            <Nav.Item>
              <Link to="/product/featured" className={style.product_title}>
                Featured
              </Link>
            </Nav.Item>
          </Nav.Link>

          <Nav.Link>
            <Nav.Item>
              <Link to="/product/sale" className={style.product_title}>
                Best Sale
              </Link>
            </Nav.Item>
          </Nav.Link>
        </Nav>

        <Nav className="nav justify-content-end col-4" id="move_page">
          <Nav.Link>
            <Nav.Item className={style.move_page} id="next_page">
              <i className="fa-regular fa-chevron-left"></i>
            </Nav.Item>
          </Nav.Link>
          <Nav.Link>
            <Nav.Item className={style.move_page} id="back_page">
              <i className="fa-regular fa-chevron-right"></i>
            </Nav.Item>
          </Nav.Link>
        </Nav>
      </Row>
    </div>
  );
}

export default ProductTitle;