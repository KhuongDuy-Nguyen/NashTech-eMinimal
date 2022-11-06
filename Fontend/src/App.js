import Login from "./screens/Login";
import Home from "./screens/Home";
import DashboardUser from "./screens/Dashboard-User";
import DashboardAdmin from "./screens/Dashboard-Admin";
import NotFound from "./screens/404NotFound";
import Auth from "./screens/401Auth";

import ManagerUser from "./components/dashboard/admin/managerUser";
import ManagerProduct from "./components/dashboard/admin/managerProduct";
import ManagerCategory from "./components/dashboard/admin/managerCategory";

import UserInfo from "./components/dashboard/user/info";
import UserCart from "./components/dashboard/user/cart";

import { Route, Routes } from "react-router-dom";

import React from "react";

import "mdb-react-ui-kit/dist/css/mdb.min.css";
import authToken from "./utils/authToken";


function App() {


  if (localStorage.getItem("token")) {
    authToken(localStorage.getItem("token"));
  }

  return (
    <div className="App">
      <Routes>
        {/* Router Home */}
        <Route exact path="/" element={<Home />} />
        <Route path="/category/:categoryName" element={<Home />} />
        <Route path="/product/:filter" element={<Home />} />

        {/* Router Login */}
        <Route path="/login" element={<Login />} />

        {/* Router Dashboard of user */}

        <Route path="/user/" element={<DashboardUser />}>
          <Route path="info" element={<UserInfo />} />
          <Route path="cart" element={<UserCart />} />
        </Route>

        {/* Router Dashboard of admin */}
        <Route path="/admin/" element={<DashboardAdmin />}>
          <Route path="manager-user" element={<ManagerUser />} />
          <Route path="manager-product" element={<ManagerProduct />} />
          <Route path="manager-category" element={<ManagerCategory />} />
        </Route>

        {/* 404 Not Found */}
        <Route path="*" element={<NotFound />} />
        <Route path="401" element={<Auth />} />
      </Routes>
    </div>
  );
}

export default App;
