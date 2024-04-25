import React, { useEffect, useState } from "react";
import { RouterProvider } from "react-router-dom";

import "./App.css";
import Navbar from "./components/common/navbar";
import router from "./routers/router";
import Footer from "./components/footer";
import UserContext from "./context/user-context";
import { getToken } from "./service/token";

function App() {
  const [accessToken, setAccessToken] = useState("");
  useEffect(() => {
    const token = getToken();
    if (token) setAccessToken(token);
  }, []);

  return (
    <div className="container">
      <UserContext.Provider value={{ accessToken, setAccessToken }}>
        <Navbar />
        <RouterProvider router={router} />
        <Footer />
      </UserContext.Provider>
    </div>
  );
}

export default App;
