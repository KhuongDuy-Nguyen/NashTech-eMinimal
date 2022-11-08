import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  LogoutOutlined,
  TagsOutlined,
  ShoppingCartOutlined,
  UsergroupAddOutlined,
} from "@ant-design/icons";

import { Layout, Menu} from "antd";
import React, { useState } from "react";
import "../styles/dashboard.css";
import logo from "../assets/images/logo.jpg";
import { Outlet} from "react-router-dom";
import { useNavigate } from "react-router-dom";
import logout from "../utils/logout";

const { Header, Footer, Sider } = Layout;


function App(){
  const [collapsed, setCollapsed] = useState(false);
  let navigate = useNavigate();
  
  if(localStorage.getItem("role") !== "ADMIN"){
    navigate("/401");
  }

  return (
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
              icon: <UsergroupAddOutlined style={{ fontSize: "20px" }} />,
              label: "Users Management",
              onClick: () => {
                navigate("/admin/manager-user");
              },
            },
            {
              key: "2",
              icon: <ShoppingCartOutlined style={{ fontSize: "20px" }} />,
              label: "Products Management",
              onClick: () => {
                navigate("/admin/manager-product");
              },
            },
            {
              key: "3",
              icon: <TagsOutlined style={{ fontSize: "20px" }} />,
              label: "Categories Management",
              onClick: () => {
                navigate("/admin/manager-category");
              },
            },

            {
              key: "4",
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

        <Outlet />

        <Footer style={{ textAlign: "center" }}>
          eMinimal ©2022 Created by Khuong Duy
        </Footer>
      </Layout>
    </Layout>
  );
};

export default App;
