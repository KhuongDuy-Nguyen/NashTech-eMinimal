import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  LogoutOutlined,
  UserOutlined,
  ShoppingCartOutlined,
  KeyOutlined,
  HomeOutlined
} from "@ant-design/icons";

import { Layout, Menu, Slider } from "antd";
import React, { useState } from "react";
import "../styles/dashboard.css";
import logo from "../assets/images/logo.jpg";
import { Outlet } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import logout from "../utils/logout";

const { Header, Content, Footer, Sider } = Layout;
const App = () => {
  let navigate = useNavigate();

  if (localStorage.getItem("role") !== "GUEST" && localStorage.getItem("role") !== "ADMIN") {
    navigate("/401");
  }

  const [collapsed, setCollapsed] = useState(false);
  return (
    <>
      <Layout style={{ minHeight: "100vh" }}>
        <Sider trigger={null} collapsible collapsed={collapsed}>
          <div className="logo">
            <img
              src={logo}
              alt="logo eMinimal"
              // cover size images
              style={{ width: "100%", height: "100%" }}
            />
          </div>
          <Menu
            theme="dark"
            mode="inline"
            defaultSelectedKeys={["1"]}
            items={[
              {
                key: "1",
                icon: <UserOutlined style={{ fontSize: "20px" }} />,
                label: "Information",
                onClick: () => {
                  navigate("/user/info");
                },
              },
              {
                key: "2",
                icon: <ShoppingCartOutlined style={{ fontSize: "20px" }} />,
                label: "Cart Management",
                onClick: () => {
                  navigate("/user/cart");
                },
              },
              {
                key: "3",
                icon: <KeyOutlined style={{ fontSize: "20px" }} />,
                label: "Change Password",
                onClick: () => {
                  navigate("/user/changePassword");
                },
              },
              {
                key: "4",
                icon: <HomeOutlined style={{ fontSize: "20px" }}/>,
                label: "Back to Home",
                onClick: () => {
                  window.location.href = "/";
                },
              },
              {
                key: "5",
                icon: <LogoutOutlined style={{ fontSize: "20px" }} />,
                label: "Logout",
                onClick: () => {
                  logout();
                },
              },
            ]}
          />
        </Sider>
        <Layout className="site-layout">
          <Header
            className="site-layout-background"
            style={{
              padding: 0,
            }}
          >
            {React.createElement(
              collapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
              {
                className: "trigger",
                onClick: () => setCollapsed(!collapsed),
              }
            )}
          </Header>
          <Content
            className="site-layout-background"
            style={{
              margin: "24px 16px",
              padding: 24,
              minHeight: 280,
            }}
          >
            <Outlet />
          </Content>
          <Footer style={{ textAlign: "center" }}>
            eMinimal ©2022 Created by Khuong Duy
          </Footer>
        </Layout>
      </Layout>
    </>
  );
};
export default App;
