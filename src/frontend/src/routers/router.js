import React from "react";
import { createBrowserRouter } from "react-router-dom";

import Home from "../components/home";
import Registration from "../components/registration";
import Login from "../components/login";
import Error from "../components/common/error";
import Logout from "../components/logout";

import Clients from "../components/home";
import AddCient from "../components/home";
import EditClient from "../components/home";
import Devices from "../components/home";
import AddDevice from "../components/home";
import EditDevice from "../components/home";
import Measurements from "../components/measurements";
import AddMeasurement from "../components/home";

export default createBrowserRouter([
  {
    path: "/",
    element: <Home />,
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
    path: "/clients",
    element: <Clients />,
  },
  {
    path: "/clients/add",
    element: <AddCient />,
  },
  {
    path: "/clients/edit/:clientId",
    element: <EditClient />,
  },
  {
    path: "/devices",
    element: <Devices />,
  },
  {
    path: "/devices/add",
    element: <AddDevice />,
  },
  {
    path: "/devices/edit/:deviceId",
    element: <EditDevice />,
  },
  {
    path: "/measurements/:deviceId",
    element: <Measurements />,
  },
  {
    path: "/measurements/add/:deviceId",
    element: <AddMeasurement />,
  },
]);
