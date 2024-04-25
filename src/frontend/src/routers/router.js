import React from "react";
import { createBrowserRouter } from "react-router-dom";
import Product from "../components/products";
import Registration from "../components/registration";
import Login from "../components/login";
import History from "../components/history";
import AddProduct from "../components/my-products/add-product";
import ProductDetail from "../components/products/product-detail";
import Error from "../components/common/error";
import Logout from "../components/logout";
import MyProducts from "../components/my-products";
import EditProduct from "../components/my-products/edit-product";

export default createBrowserRouter([
  {
    path: "/",
    element: <Product />,
    errorElement: <Error />,
  },
  {
    path: "/register",
    element: <Registration />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/logout",
    element: <Logout />,
  },
  {
    path: "/history",
    element: <History />,
  },
  {
    path: "/my-products",
    element: <MyProducts />,
  },
  {
    path: "/my-products/add",
    element: <AddProduct />,
  },
  {
    path: "/my-products/edit/:productId",
    element: <EditProduct />,
  },
  {
    path: "/products/:productId",
    element: <ProductDetail />,
  },
]);
