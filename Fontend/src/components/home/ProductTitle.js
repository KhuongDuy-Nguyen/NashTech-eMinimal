import React from "react";
import { Nav, Row } from "react-bootstrap";
import style from "../../styles/productTitle.module.css";
import { Dropdown, Input } from "antd";
import { SearchOutlined, DownOutlined } from "@ant-design/icons";
import { Link, useNavigate } from "react-router-dom";

const { Search } = Input;

function ProductTitle() {
  let navigator = useNavigate();


  // TODO: Search product by name
  const onSearch = (value) => {
    navigator(`/product/search/${value}`);
  };

  const menu = (
    <Nav>
      <Nav.Link as={Link} to="/product/cost-desc">
        <Nav.Item className={style.product_title}>
          Cost <i className="fa-solid fa-arrow-down-wide-short"></i>
        </Nav.Item>
      </Nav.Link>

      <Nav.Link as={Link} to="/product/cost-asc">
        <Nav.Item className={style.product_title}>
          Cost <i className="fa-solid fa-arrow-down-short-wide"></i>
        </Nav.Item>
      </Nav.Link>

      <Nav.Link as={Link} to="/product/name-desc">
        <Nav.Item className={style.product_title}>
          Name <i class="fa-solid fa-arrow-down-z-a"></i>
        </Nav.Item>
      </Nav.Link>

      <Nav.Link as={Link} to="/product/name-asc">
        <Nav.Item className={style.product_title}>
          Name <i class="fa-solid fa-arrow-down-a-z"></i>
        </Nav.Item>
      </Nav.Link>
    </Nav>
  );

  return (
    <div id="product_title" className="mb-5">
      <h3 className={style.product_title_h3} id="section_shop">
        out products
      </h3>

      <Row>
        <Nav className="nav col-8" id="nav_product">
          <Nav.Link as={Link} to="/">
            <Nav.Item className={style.product_title}>All</Nav.Item>
          </Nav.Link>

          <Nav.Link as={Link} to="/product/featured">
            <Nav.Item className={style.product_title}>Featured</Nav.Item>
          </Nav.Link>

          <Nav.Link as={Link} to="/product/sale">
            <Nav.Item className={style.product_title}>Best Sale</Nav.Item>
          </Nav.Link>

          <Nav.Link as={Link} to="/">
            <Dropdown overlay={menu} placement="bottomLeft">
              <Nav.Item className={style.product_title}>
                Sort by <i class="fa-solid fa-sort-down" style={ { marginLeft: '5px' } }></i>
              </Nav.Item>
            </Dropdown>
          </Nav.Link>
        </Nav>

        {/* Button search */}
        <div className="col-4">
          <Search
            placeholder="Search product name"
            onSearch={onSearch}
            size="large"
            enterButton={<SearchOutlined style={{ padding: "5px" }} />}
          />
        </div>
      </Row>
    </div>
  );
}

export default ProductTitle;
