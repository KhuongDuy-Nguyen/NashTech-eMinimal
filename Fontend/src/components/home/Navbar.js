import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Button, Dropdown, Menu } from "antd";
import style from "../../styles/navbar.module.css";
import "../../styles/navbar.module.css";
import { Badge, Modal } from "react-bootstrap";
import { useState } from "react";
import logout from "../../utils/logout";
import { useEffect } from "react";
import { LogoutOutlined, HomeOutlined } from "@ant-design/icons";
import { getCartByCartId, getCartWhenStatusIsFalse } from "../../services/cart";
import CartProduct from "./CartProduct";
import convert from "../../utils/groupByCartProduct";
import logo from "../../assets/images/Logo.png";
import ShowMessage from "../../utils/message";

function NavbarMenu() {
  const [showAdmin, setShowAdmin] = useState(false);
  const [showUser, setShowUser] = useState(false);

  const [cost, setCost] = useState([]);
  const [numberItem, setNumberItem] = useState(0);
  const [data, setData] = useState([]);

  // For modal and transfer data to modal
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);

  useEffect(() => {
    if (localStorage.getItem("role") === "ADMIN") {
      setShowAdmin(true);
    }

    if (localStorage.getItem("userId") !== null) {
      setShowUser(true);
    }

    if (localStorage.getItem("cartId") === null) {
      getCartWhenStatusIsFalse(localStorage.getItem("userId"))
        .then((res) => {
          localStorage.setItem("cartId", res.data.cartID);
        })
        .catch((err) => {
          console.log(err);
        });
    }

    if (localStorage.getItem("cartId") !== null) {
      getCartByCartId(localStorage.getItem("cartId"))
        .then((res) => {
          setCost(res.data.price);
          setNumberItem(res.data.cartProducts.length);

          // Set data for modal
          setData(res.data.cartProducts);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, []);

  const menuUser = (
    <Menu
      items={[
        {
          key: "1",
          label: <Button href="/user/info">Dashboard</Button>,
          icon: <HomeOutlined />,
        },
        {
          key: "2",
          label: (
            <Button
              onClick={() => {
                logout();
              }}
            >
              Logout
            </Button>
          ),
          icon: <LogoutOutlined />,
        },
      ]}
    />
  );

  const handleShow = () => {
    console.log("SHOW");
    // const group = convert(data);
    var group = convert(data);
    console.log(Object.values(group));
    setData(Object.values(group));

    setShow(true);
  };

  const handlePayment = () => {
    ShowMessage("success", "Payment successfully");
    setShow(false);
  };

  return (
    <>
      <Navbar bg="light" sticky="top">
        <Container>
          <Navbar.Brand href="/">
            <img src={logo} alt="" className={style.name_logo} />
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="m-auto">
              <Nav.Link href="/">
                <Nav.Item className={style.navbar_info}>Home</Nav.Item>
              </Nav.Link>

              <Nav.Link href="#product_title">
                <Nav.Item className={style.navbar_info}>Shop</Nav.Item>
              </Nav.Link>

              <Nav.Link href="#about">
                <Nav.Item className={style.navbar_info}>About</Nav.Item>
              </Nav.Link>

              <Nav.Link
                href="/admin"
                style={showAdmin ? {} : { display: "none" }}
              >
                <Nav.Item className={style.navbar_info}>Admin</Nav.Item>
              </Nav.Link>
            </Nav>

            <div
              className={style.user}
              style={{ display: showUser === true ? "" : "none" }}
            >
              <Dropdown overlay={menuUser}>
                <Button
                  variant="outline-primary"
                  className={style.btn_user}
                  id="btn_user"
                >
                  <i className="fa-duotone fa-user fa-lg"></i>
                </Button>
              </Dropdown>

              <Button
                variant="outline-primary"
                className={style.btn_user}
                id="btn_cart"
                onClick={() => {
                  handleShow();
                }}
              >
                <i className="fa-duotone fa-cart-shopping fa-lg me-2"></i>
                <Badge bg="secondary" id="num_cart">
                  {numberItem}
                </Badge>
              </Button>

              <Button
                variant="outline-primary"
                className={style.btn_user}
                id="cost"
              >
                $<span id="total_cost">{cost}</span>
              </Button>
            </div>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>List product</Modal.Title>
        </Modal.Header>
        <Modal.Body className="p-0">
          {/* ===> List product in cart <===*/}

          {data.map((item) => (
            // Count product have same id in cart

            <CartProduct
              key={item.productID}
              productID={item.productID}
              name={item.productName}
              price={item.productCost}
              quantity={item.quantity}
            />
          ))}

          {/* ============================= */}
        </Modal.Body>
        <Modal.Footer>
          <p>
            <strong>Total Cost: ${cost}</strong>
          </p>

          <Button type="primary" onClick={handlePayment}>
            Payment now
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default NavbarMenu;
