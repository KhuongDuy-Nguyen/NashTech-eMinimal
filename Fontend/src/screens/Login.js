import React, { useState } from "react";
import {
  MDBContainer,
  MDBTabs,
  MDBTabsItem,
  MDBTabsLink,
  MDBTabsContent,
  MDBTabsPane,
  MDBBtn,
  MDBIcon,
  MDBInput,
  MDBCheckbox,
  MDBRow,
  MDBCard,
  MDBCardBody,
} from "mdb-react-ui-kit";
import { Alert } from "antd";
import background from "../assets/images/backgroundLogin.png";
import { Form, Route } from "react-router-dom";
import axios from "axios";
import { Toast } from "react-bootstrap";


function App() {
  const [justifyActive, setJustifyActive] = useState("tab1");

  const Body = {
    // backgroundColor: "red",
    backgroundImage: "url(" + background + ")",
    backgroundSize: "cover",
    height: "100vh",
  };

  const handleJustifyClick = (value) => {
    if (value === justifyActive) {
      return;
    }

    setJustifyActive(value);
  };
  const [show, setShow] = useState(false);
  const [message, setMessage] = useState("");
  const [type, setType] = useState("");
  const [login, setLogin] = useState(false);

  const handleSubmitLogin = (e) => {
    console.log(e);
    e.preventDefault();
    var username = e.target.username.value;
    var password = e.target.password.value;

    axios
      .post("http://localhost:8080/api/auth/login", {
        userName: username,
        userPassword: password,
      })
      .then((res) => {

        setShow(true)
        setMessage("Login Success")
        setType("success")

        localStorage.setItem("token", JSON.stringify(res.data.token).replace(/"/g, ""));
        localStorage.setItem("userId", JSON.stringify(res.data.userId).replace(/"/g, ""));
        localStorage.setItem("role", JSON.stringify(res.data.userRole).replace(/"/g, ""));

        // Auto redirect to home page
        setTimeout(() => {
          window.location.href = "/";
        }, 1000);
      })
      .catch((err) => {
        var message = err.response.data.message;
        setShow(true)
        setMessage(message)
        setType("danger")
      });
  };

  const handleSubmitRegister = (e) => {
    e.preventDefault();
    console.log(
      e.target.email.value,
      e.target.username.value,
      e.target.password.value,
      e.target.confirmPassword.value
    );

    if (e.target.password.value !== e.target.confirmPassword.value) {
      setShow(true)
      setMessage("Password not match")
      setType("danger")
    }else{
      axios
      .post("http://localhost:8080/api/auth/register", {
        userName: e.target.username.value,
        userEmail: e.target.email.value,
        userPassword: e.target.password.value,
      })
      .then((res) => {
        setShow(true)
        setMessage("Register Success")
        setType("success")
        handleJustifyClick("tab1");
      })
      .catch((err) => {
        var message = err.response.data.message;
        setShow(true)
        setMessage(message)
        setType("danger")
      });
    }
  };

  return (
    <MDBContainer fluid style={Body}>
      <MDBRow className="d-flex justify-content-center align-items-center h-100">
        <MDBCard
          className="bg-white my-5 mx-auto"
          style={{ borderRadius: "1rem", maxWidth: "500px" }}
        >
          <Toast
            onClose={() => setShow(false)}
            show={show}
            delay={2000}
            autohide
            bg={type}
          >
            <Toast.Body className="text-white">{message}</Toast.Body>
          </Toast>

          <MDBCardBody className="p-5 w-100 d-flex flex-column">
            {/* <Alert message={error} type="error" /> */}
            <MDBContainer>
              <MDBTabs
                pills
                justify
                className="mb-3 d-flex flex-row justify-content-between"
              >
                <MDBTabsItem>
                  <MDBTabsLink
                    onClick={() => handleJustifyClick("tab1")}
                    active={justifyActive === "tab1"}
                  >
                    Login
                  </MDBTabsLink>
                </MDBTabsItem>
                <MDBTabsItem>
                  <MDBTabsLink
                    onClick={() => handleJustifyClick("tab2")}
                    active={justifyActive === "tab2"}
                  >
                    Register
                  </MDBTabsLink>
                </MDBTabsItem>
              </MDBTabs>

              <MDBTabsContent>
                <MDBTabsPane show={justifyActive === "tab1"}>
                  <div className="text-center mb-3">
                    <p>Sign in with:</p>

                    <div
                      className="d-flex justify-content-between mx-auto"
                      style={{ width: "40%" }}
                    >
                      <MDBBtn
                        tag="a"
                        color="none"
                        className="m-1"
                        style={{ color: "#1266f1" }}
                      >
                        <MDBIcon fab icon="facebook-f" size="lg" />
                      </MDBBtn>

                      <MDBBtn
                        tag="a"
                        color="none"
                        className="m-1"
                        style={{ color: "red" }}
                      >
                        <MDBIcon fab icon="google" size="lg" />
                      </MDBBtn>
                    </div>

                    <p className="text-center mt-3">or:</p>
                  </div>

                  <form onSubmit={handleSubmitLogin}>
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Your username"
                      id="usernameLogin"
                      type="text"
                      name="username"
                      required
                      // onChange={(e) => setEmail(e.target.value)}
                    />
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Password"
                      id="PasswordLogin"
                      type="password"
                      name="password"
                      required
                      // onChange={(e) => setPassword(e.target.value)}
                    />

                    <div className="d-flex justify-content-between mx-4 mb-4">
                      <MDBCheckbox
                        name="flexCheck"
                        value=""
                        id="flexCheckDefault"
                        label="Remember me"
                      />
                      <a href="!#">Forgot password?</a>
                    </div>

                    <MDBBtn
                      className="mb-4 w-100"
                      type="submit"
                      // onSubmit={handleSubmit}
                    >
                      Sign in
                    </MDBBtn>
                  </form>
                </MDBTabsPane>

                <MDBTabsPane show={justifyActive === "tab2"}>
                  <div className="text-center mb-3">
                    <p>Sign up with:</p>

                    <div
                      className="d-flex justify-content-between mx-auto"
                      style={{ width: "40%" }}
                    >
                      <MDBBtn
                        tag="a"
                        color="none"
                        className="m-1"
                        style={{ color: "#1266f1" }}
                      >
                        <MDBIcon fab icon="facebook-f" size="lg" />
                      </MDBBtn>

                      <MDBBtn
                        tag="a"
                        color="none"
                        className="m-1"
                        style={{ color: "red" }}
                      >
                        <MDBIcon fab icon="google" size="lg" />
                      </MDBBtn>
                    </div>

                    <p className="text-center mt-3">or:</p>
                  </div>

                  <form onSubmit={handleSubmitRegister}>
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Username"
                      id="usernameRegister"
                      type="text"
                      name="username"
                      required
                      // onChange={(e) => setUsername(e.target.value)}
                    />
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Email"
                      id="emailRegister"
                      type="email"
                      name="email"
                      required
                      // onChange={(e) => setEmail(e.target.value)}
                    />
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Password"
                      id="passwordRegister"
                      type="password"
                      name="password"
                      required
                      // onChange={(e) => setPassword(e.target.value)}
                    />
                    <MDBInput
                      wrapperClass="mb-4"
                      label="Confirm Password"
                      id="confirmPassword"
                      type="password"
                      name="confirmPassword"
                      required
                      // onChange={(e) => setConfirmPassword(e.target.value)}
                    />

                    <MDBBtn
                      className="mb-4 w-100"
                      type="submit"
                      // onSubmit={handleSubmit}
                    >
                      Sign up
                    </MDBBtn>
                  </form>
                </MDBTabsPane>
              </MDBTabsContent>
            </MDBContainer>
          </MDBCardBody>
        </MDBCard>
      </MDBRow>
    </MDBContainer>
  );
}

export default App;
