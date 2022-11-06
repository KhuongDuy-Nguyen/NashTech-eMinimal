import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Button } from "antd";
import style from "../../styles/navbar.module.css";
import "../../styles/navbar.module.css";
import { Badge, ListGroup, Overlay } from "react-bootstrap";
import { useState, useRef } from "react";
import Popover from 'react-bootstrap/Popover';

function NavbarMenu() {
  const [show, setShow] = useState(false);
  const [target, setTarget] = useState(null);
  const ref = useRef(null);

  const handleClick = (event) => {
    setShow(!show);
    setTarget(event.target);
  };


  return (
    <Navbar bg="light" fixed="top">
      <Container>
        <Navbar.Brand className={style.name_logo} href="#home">
          eMinimal
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="m-auto">
            <Nav.Link href="#home">
              <Nav.Item className={style.navbar_info}>Home</Nav.Item>
            </Nav.Link>

            <Nav.Link href="#info">
              <Nav.Item className={style.navbar_info}>Shop</Nav.Item>
            </Nav.Link>

            <Nav.Link href="#about">
              <Nav.Item className={style.navbar_info}>About</Nav.Item>
            </Nav.Link>
          </Nav>

          {/* <div id="search" className="me-5">
                <InputGroup>
                  <Form.Control placeholder="Search something" />
                  <Button variant="outline-secondary" className={style.btn_search}><i className="fa-duotone fa-magnifying-glass fa-lg"></i></Button>
                </InputGroup>
              </div> */}

          <div className={style.user}>
            <div ref={ref} className="d-inline">
              <Button
                variant="outline-primary"
                className={style.btn_user}
                id="btn_user"
                onClick={handleClick}
              >
                <i className="fa-duotone fa-user fa-lg"></i>
              </Button>

              <Overlay
                show={show}
                target={target}
                placement="bottom"
                container={ref}
                containerPadding={20}
              >
                <Popover id="popover-contained">
                  <Popover.Header as="h3">Account</Popover.Header>
                  <Popover.Body>
                    <ListGroup>
                      <ListGroup.Item action href="#link1">
                        <i class="fa-regular fa-user-pen fa-lg"></i>{" "}
                        Profile
                      </ListGroup.Item>

                      <ListGroup.Item action href="#link2">
                        <i class="fa-solid fa-bags-shopping fa-lg"></i>{" "}
                        List cart
                      </ListGroup.Item>

                      <ListGroup.Item action href="#link3">
                        <i class="fa-solid fa-person-through-window fa-lg"></i>{" "}
                        Logout
                      </ListGroup.Item>
                    </ListGroup>
                  </Popover.Body>
                </Popover>
              </Overlay>
            </div>

            <Button
              variant="outline-primary"
              className={style.btn_user}
              id="btn_cart"
            >
              <i className="fa-duotone fa-cart-shopping fa-lg me-2"></i>
              <Badge bg="secondary" id="num_cart">
                0
              </Badge>
            </Button>

            <Button
              variant="outline-primary"
              className={style.btn_user}
              id="cost"
            >
              $<span id="total_cost">0.0</span>
            </Button>
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarMenu;
